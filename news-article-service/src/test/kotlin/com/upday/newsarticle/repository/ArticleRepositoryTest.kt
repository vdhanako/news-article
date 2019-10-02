package com.upday.newsarticle.repository

import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.entity.AuthorEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import java.sql.Date

@RunWith(SpringRunner::class)
@ContextConfiguration
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private lateinit var repository: ArticleRepository

    @Test
    fun `get all articles`() {
        val response = repository.findAll()

        assertThat(response.size, `is`(equalTo(3)))
    }

    @Test
    fun `get one article by id`() {
        val response = repository.findById(1)

        assertThat(response.get().articleId, `is`(equalTo(1L)))
        assertThat(response.get().header, `is`(equalTo("some header")))
        assertThat(response.get().shortDescription, `is`(equalTo("some description")))
        assertThat(response.get().text, `is`(equalTo("some text")))
        assertThat(response.get().publishDate, `is`(notNullValue()))
        assertThat(response.get().author.authorId, `is`(equalTo(1L)))
        assertThat(response.get().author.authorName, `is`(equalTo("venkat")))
        assertThat(response.get().keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `get articles by author`() {
        val response = repository.findByAuthor(1)

        assertThat(response.get(0).articleId, `is`(equalTo(1L)))
        assertThat(response.get(0).header, `is`(equalTo("some header")))
        assertThat(response.get(0).shortDescription, `is`(equalTo("some description")))
        assertThat(response.get(0).text, `is`(equalTo("some text")))
        assertThat(response.get(0).publishDate, `is`(notNullValue()))
        assertThat(response.get(0).author.authorId, `is`(equalTo(1L)))
        assertThat(response.get(0).author.authorName, `is`(equalTo("venkat")))
        assertThat(response.get(0).keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `get articles for a keyword`() {
        val response = repository.findByKeyword("some")

        assertThat(response.get(0).articleId, `is`(equalTo(1L)))
        assertThat(response.get(0).header, `is`(equalTo("some header")))
        assertThat(response.get(0).shortDescription, `is`(equalTo("some description")))
        assertThat(response.get(0).text, `is`(equalTo("some text")))
        assertThat(response.get(0).publishDate, `is`(notNullValue()))
        assertThat(response.get(0).author.authorId, `is`(equalTo(1L)))
        assertThat(response.get(0).author.authorName, `is`(equalTo("venkat")))
        assertThat(response.get(0).keywords?.first(), `is`(equalTo("some")))
    }

    @Test
    fun `create and delete one article for a keyword`() {
        val date = Date(2019,10,1)
        val authorEntity = AuthorEntity(2, "robert")
        val keywords = mutableSetOf("some")
        val articleEntity = ArticleEntity(4, "some header", "some description", "some text", date, authorEntity, keywords)

        val response = repository.save(articleEntity)

        assertThat(response.articleId, `is`(equalTo(4L)))
        assertThat(response.header, `is`(equalTo("some header")))
        assertThat(response.shortDescription, `is`(equalTo("some description")))
        assertThat(response.text, `is`(equalTo("some text")))
        assertThat(response.publishDate, `is`(notNullValue()))
        assertThat(response.author.authorId, `is`(equalTo(2L)))
        assertThat(response.author.authorName, `is`(equalTo("robert")))
        assertThat(response.keywords?.size, `is`(equalTo(1)))

        repository.deleteById(4)
    }

}