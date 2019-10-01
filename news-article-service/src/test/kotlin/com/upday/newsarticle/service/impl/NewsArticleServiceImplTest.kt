package com.upday.newsarticle.service.impl

import com.nhaarman.mockito_kotlin.whenever
import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.domain.Author
import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.entity.AuthorEntity
import com.upday.newsarticle.exception.ArticleNotFoundException
import com.upday.newsarticle.repository.ArticleRepository
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.sql.Date
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class NewsArticleServiceImplTest {

    @InjectMocks
    lateinit var service: NewsArticleServiceImpl

    @Mock
    lateinit var repository: ArticleRepository

    private lateinit var articleEntity: ArticleEntity
    private lateinit var article: Article

    private lateinit var articleEntities: MutableList<ArticleEntity>
    private lateinit var articles: MutableList<Article>

    @Before
    fun setup() {
        var date = Date(2019,10,1)

        var author = Author(1, "some author")
        var authorEntity = AuthorEntity(1, "some author")

        var keywords = mutableSetOf("some")

        article = Article(1, "some header", "some description", "some text", date, author, keywords)
        articles = mutableListOf(article)

        articleEntity = ArticleEntity(1, "some header", "some description", "some text", date, authorEntity, keywords)
        articleEntities = mutableListOf(articleEntity)
    }

    @Test
    fun `getAllArticles should get all articles`() {
        whenever(repository.findAll()).thenReturn(articleEntities)

        var response = service.getAllArticles()

        assertThat(response.size, `is`(equalTo(1)))
        assertThat(response[0].articleId, `is`(equalTo(1L)))
        assertThat(response[0].header, `is`(equalTo("some header")))
        assertThat(response[0].shortDescription, `is`(equalTo("some description")))
        assertThat(response[0].text, `is`(equalTo("some text")))
        assertThat(response[0].publishDate, `is`(notNullValue()))
        assertThat(response[0].author.authorId, `is`(equalTo(1L)))
        assertThat(response[0].author.authorName, `is`(equalTo("some author")))
        assertThat(response[0].keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `getArticle should get one article for a given article id`() {
        whenever(repository.findById(1)).thenReturn(Optional.of(articleEntity))

        var response = service.getArticle(1)

        assertThat(response.articleId, `is`(equalTo(1L)))
        assertThat(response.header, `is`(equalTo("some header")))
        assertThat(response.shortDescription, `is`(equalTo("some description")))
        assertThat(response.text, `is`(equalTo("some text")))
        assertThat(response.publishDate, `is`(notNullValue()))
        assertThat(response.author.authorId, `is`(equalTo(1L)))
        assertThat(response.author.authorName, `is`(equalTo("some author")))
        assertThat(response.keywords?.first(), `is`(equalTo("some")))
    }

    @Test(expected = ArticleNotFoundException::class)
    fun `getArticle should throw ArticleNotFoundException if an article is not found`() {
        whenever(repository.findById(1)).thenThrow(ArticleNotFoundException("article with 1 was not found"))

        service.getArticle(1)
    }

    @Test
    fun `getArticleByAuthor should get all articles of an author id`() {
        whenever(repository.findByAuthor(1)).thenReturn(articleEntities)

        var response = service.getArticleByAuthor(1)

        assertThat(response.size, `is`(equalTo(1)))
        assertThat(response[0].articleId, `is`(equalTo(1L)))
        assertThat(response[0].header, `is`(equalTo("some header")))
        assertThat(response[0].shortDescription, `is`(equalTo("some description")))
        assertThat(response[0].text, `is`(equalTo("some text")))
        assertThat(response[0].publishDate, `is`(notNullValue()))
        assertThat(response[0].author.authorId, `is`(equalTo(1L)))
        assertThat(response[0].author.authorName, `is`(equalTo("some author")))
        assertThat(response[0].keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `getArticleByKeyword should get all articles for a specific keyword`() {
        whenever(repository.findByAuthor(1)).thenReturn(articleEntities)

        var response = service.getArticleByAuthor(1)

        assertThat(response.size, `is`(equalTo(1)))
        assertThat(response[0].articleId, `is`(equalTo(1L)))
        assertThat(response[0].header, `is`(equalTo("some header")))
        assertThat(response[0].shortDescription, `is`(equalTo("some description")))
        assertThat(response[0].text, `is`(equalTo("some text")))
        assertThat(response[0].publishDate, `is`(notNullValue()))
        assertThat(response[0].author.authorId, `is`(equalTo(1L)))
        assertThat(response[0].author.authorName, `is`(equalTo("some author")))
        assertThat(response[0].keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `createArticle should create an article`() {
        whenever(repository.save(articleEntity)).thenReturn(articleEntity)

        var response = service.createArticle(article)

        assertThat(response.articleId, `is`(equalTo(1L)))
        assertThat(response.header, `is`(equalTo("some header")))
        assertThat(response.shortDescription, `is`(equalTo("some description")))
        assertThat(response.text, `is`(equalTo("some text")))
        assertThat(response.publishDate, `is`(notNullValue()))
        assertThat(response.author.authorId, `is`(equalTo(1L)))
        assertThat(response.author.authorName, `is`(equalTo("some author")))
        assertThat(response.keywords?.first(), `is`(equalTo("some")))
    }

    @Test(expected = ArticleNotFoundException::class)
    fun `createArticle should throw ArticleNotFoundException when article cannot be created`() {
        whenever(repository.save(articleEntity)).thenThrow(ArticleNotFoundException("article with 1 was not found"))

        service.createArticle(article)
    }

    @Test
    fun `deleteArticle should delete an article`() {
        whenever(repository.existsById(1)).thenReturn(true)

        service.deleteArticle(1)
    }

    @Test(expected = ArticleNotFoundException::class)
    fun `deleteArticle should throw ArticleNotFoundException and not delete an article`() {
        whenever(repository.existsById(1)).thenReturn(false)

        service.deleteArticle(1)
    }

}