package com.upday.newsarticle.service.impl

import com.nhaarman.mockito_kotlin.whenever
import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.domain.Author
import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.entity.AuthorEntity
import com.upday.newsarticle.exception.ArticleCreationException
import com.upday.newsarticle.exception.ArticleNotFoundException
import com.upday.newsarticle.mapper.ArticleObjectMapper
import com.upday.newsarticle.repository.ArticleRepository
import org.hamcrest.Matchers.*
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
    private lateinit var service: NewsArticleServiceImpl

    @Mock
    private lateinit var repository: ArticleRepository

    @Mock
    private lateinit var articleObjectMapper: ArticleObjectMapper

    private lateinit var articleEntity: ArticleEntity
    private lateinit var article: Article

    private lateinit var articleEntities: MutableList<ArticleEntity>
    private lateinit var articles: MutableList<Article>

    @Before
    fun setup() {
        val date = Date(2019,10,1)

        val author = Author(1, "some author")
        val authorEntity = AuthorEntity(1, "some author")

        val keywords = mutableSetOf("some")

        article = Article(1, "some header", "some description", "some text", date, author, keywords)
        articles = mutableListOf(article)

        articleEntity = ArticleEntity(1, "some header", "some description", "some text", date, authorEntity, keywords)
        articleEntities = mutableListOf(articleEntity)
    }

    @Test
    fun `getAllArticles should get all articles`() {
        whenever(repository.findAll()).thenReturn(articleEntities)
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.getAllArticles()

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
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.getArticle(1)

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
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.getArticleByAuthor(1)

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
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.getArticleByAuthor(1)

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
    fun `getArticleByPeriod should get all articles between two dates`() {
        val from = Date(2019, 10, 1)
        val to = Date(2019, 10, 1)
        whenever(repository.findByPeriod(from, to)).thenReturn(articleEntities)
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.getArticleByPeriod(from, to)

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
        whenever(repository.existsById(article.articleId)).thenReturn(false)
        whenever(articleObjectMapper.fromArticle(article)).thenReturn(articleEntity)
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.createArticle(article)

        assertThat(response.articleId, `is`(equalTo(1L)))
        assertThat(response.header, `is`(equalTo("some header")))
        assertThat(response.shortDescription, `is`(equalTo("some description")))
        assertThat(response.text, `is`(equalTo("some text")))
        assertThat(response.publishDate, `is`(notNullValue()))
        assertThat(response.author.authorId, `is`(equalTo(1L)))
        assertThat(response.author.authorName, `is`(equalTo("some author")))
        assertThat(response.keywords?.first(), `is`(equalTo("some")))
    }

    @Test(expected = ArticleCreationException::class)
    fun `createArticle should throw ArticleCreationException when an article already exist`() {
        whenever(repository.existsById(article.articleId)).thenReturn(true)

        service.createArticle(article)
    }

    @Test
    fun `updateArticle should update an existing article`() {
        whenever(repository.save(articleEntity)).thenReturn(articleEntity)
        whenever(articleObjectMapper.fromArticle(article)).thenReturn(articleEntity)
        whenever(articleObjectMapper.toArticle(articleEntity)).thenReturn(article)

        val response = service.updateArticle(article)

        assertThat(response.articleId, `is`(equalTo(1L)))
        assertThat(response.header, `is`(equalTo("some header")))
        assertThat(response.shortDescription, `is`(equalTo("some description")))
        assertThat(response.text, `is`(equalTo("some text")))
        assertThat(response.publishDate, `is`(notNullValue()))
        assertThat(response.author.authorId, `is`(equalTo(1L)))
        assertThat(response.author.authorName, `is`(equalTo("some author")))
        assertThat(response.keywords?.first(), `is`(equalTo("some")))
    }

    @Test(expected = ArticleCreationException::class)
    fun `updateArticle should throw ArticleCreationException when the article was not updated`() {
        whenever(repository.save(articleEntity)).thenThrow(RuntimeException())
        whenever(articleObjectMapper.fromArticle(article)).thenReturn(articleEntity)

        service.updateArticle(article)
    }

    @Test
    fun `deleteArticle should delete an article`() {
        service.deleteArticle(1)
    }

    @Test(expected = ArticleNotFoundException::class)
    fun `deleteArticle should throw ArticleNotFoundException and not delete an article`() {
        whenever(repository.deleteById(article.articleId)).thenThrow(RuntimeException())
        service.deleteArticle(1)
    }

}