package com.upday.newsarticle.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.whenever
import com.upday.newsarticle.controller.advice.ArticleNotCreatedExceptionAdvice
import com.upday.newsarticle.controller.advice.ArticleNotFoundExceptionAdvice
import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.domain.Author
import com.upday.newsarticle.exception.ArticleNotCreatedException
import com.upday.newsarticle.exception.ArticleNotFoundException
import com.upday.newsarticle.service.api.NewsArticleService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.MediaType
import java.sql.Date
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(MockitoJUnitRunner::class)
class NewsArticleControllerTest {

    @InjectMocks
    private lateinit var controller: NewsArticleController

    @Mock
    private lateinit var service: NewsArticleService

    lateinit var mockMvc: MockMvc

    private var objectMapper = jacksonObjectMapper()

    private lateinit var articles: List<Article>
    private lateinit var article: Article

    @Before
    fun setup() {
        var date = Date(2019,10,1)
        var author = Author(1, "some author")
        var keywords = mutableSetOf("some")
        article = Article(1, "some header", "some description", "some text", date, author, keywords)
        articles = listOf(article)

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(
                        ArticleNotCreatedExceptionAdvice(),
                        ArticleNotFoundExceptionAdvice()).build()
    }

    @Test
    fun `getAllArticles should get all articles`() {
        whenever(service.getAllArticles()).thenReturn(articles)

        mockMvc.perform(MockMvcRequestBuilders.get("/article"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("[0].articleId").value(1))
                .andExpect(jsonPath("[0].header").value("some header"))
                .andExpect(jsonPath("[0].shortDescription").value("some description"))
                .andExpect(jsonPath("[0].text").value("some text"))
                .andExpect(jsonPath("[0].publishDate").exists())
                .andExpect(jsonPath("[0].author.authorId").value(1))
                .andExpect(jsonPath("[0].author.authorName").value("some author"))
                .andExpect(jsonPath("[0].keywords.[0]").value("some"))
    }

    @Test
    fun `getArticle should return success status and get an article for valid article id`() {
        whenever(service.getArticle(1)).thenReturn(article)

        mockMvc.perform(MockMvcRequestBuilders.get("/article/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("articleId").value(1))
                .andExpect(jsonPath("header").value("some header"))
                .andExpect(jsonPath("shortDescription").value("some description"))
                .andExpect(jsonPath("text").value("some text"))
                .andExpect(jsonPath("publishDate").exists())
                .andExpect(jsonPath("author.authorId").value(1))
                .andExpect(jsonPath("author.authorName").value("some author"))
                .andExpect(jsonPath("keywords.[0]").value("some"))
    }

    @Test
    fun `getArticle should return not found status for invalid article id`() {
        whenever(service.getArticle(1)).thenThrow(ArticleNotFoundException(""))

        mockMvc.perform(MockMvcRequestBuilders.get("/article/1"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `getArticleByAuthor should return success status and get articles for valid author id`() {
        whenever(service.getArticleByAuthor(1)).thenReturn(articles)

        mockMvc.perform(MockMvcRequestBuilders.get("/article/author/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("[0].articleId").value(1))
                .andExpect(jsonPath("[0].header").value("some header"))
                .andExpect(jsonPath("[0].shortDescription").value("some description"))
                .andExpect(jsonPath("[0].text").value("some text"))
                .andExpect(jsonPath("[0].publishDate").exists())
                .andExpect(jsonPath("[0].author.authorId").value(1))
                .andExpect(jsonPath("[0].author.authorName").value("some author"))
                .andExpect(jsonPath("[0].keywords.[0]").value("some"))
    }

    @Test
    fun `getArticleByKeyword should return success status and get articles for a specific keyword`() {
        whenever(service.getArticleByKeyword("some")).thenReturn(articles)

        mockMvc.perform(MockMvcRequestBuilders.get("/article/keyword/some"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("[0].articleId").value(1))
                .andExpect(jsonPath("[0].header").value("some header"))
                .andExpect(jsonPath("[0].shortDescription").value("some description"))
                .andExpect(jsonPath("[0].text").value("some text"))
                .andExpect(jsonPath("[0].publishDate").exists())
                .andExpect(jsonPath("[0].author.authorId").value(1))
                .andExpect(jsonPath("[0].author.authorName").value("some author"))
                .andExpect(jsonPath("[0].keywords.[0]").value("some"))
    }

    @Test
    fun `createArticle should return success status and create an article for a valid article`() {
        whenever(service.createArticle(article)).thenReturn(article)

        var requestJson = objectMapper.writeValueAsString(article)

        mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk)
                .andExpect(jsonPath("articleId").value(1))
                .andExpect(jsonPath("header").value("some header"))
                .andExpect(jsonPath("shortDescription").value("some description"))
                .andExpect(jsonPath("text").value("some text"))
                .andExpect(jsonPath("publishDate").exists())
                .andExpect(jsonPath("author.authorId").value(1))
                .andExpect(jsonPath("author.authorName").value("some author"))
                .andExpect(jsonPath("keywords.[0]").value("some"))
    }

    @Test
    fun `createArticle should return not found status when unable to create an article`() {
        whenever(service.createArticle(article)).thenThrow(ArticleNotCreatedException(""))

        var requestJson = objectMapper.writeValueAsString(article)

        mockMvc.perform(MockMvcRequestBuilders.post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `deleteArticle should return success status and delete an article for a valid article id`() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/article/1"))
                .andExpect(status().isOk)
    }

    @Test
    fun `deleteArticle should return not found status and should not delete an article for invalid article id`() {
        whenever(service.deleteArticle(1)).thenThrow(ArticleNotFoundException(""))

        mockMvc.perform(MockMvcRequestBuilders.delete("/article/1"))
                .andExpect(status().isNotFound)
    }

}