package com.upday.newsarticle.service.impl

import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.exception.ArticleCreationException
import com.upday.newsarticle.exception.ArticleNotFoundException
import com.upday.newsarticle.mapper.ArticleObjectMapper
import com.upday.newsarticle.repository.ArticleRepository
import com.upday.newsarticle.service.api.NewsArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class NewsArticleServiceImpl : NewsArticleService {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Autowired
    lateinit var articleObjectMapper: ArticleObjectMapper

    override fun getAllArticles(): List<Article> {
        val articleEntities = articleRepository.findAll()
        return articleEntities.map { articleObjectMapper.toArticle(it) }
    }

    override fun getArticle(articleId: Long): Article {
        val articleEntity = articleRepository.findById(articleId)
        if(!articleEntity.isPresent()) {
            throw ArticleNotFoundException("An article with article id $articleId was not found")
        }
        return articleObjectMapper.toArticle(articleEntity.get())
    }

    override fun getArticleByAuthor(authorId: Long): List<Article> {
        val articleEntities = articleRepository.findByAuthor(authorId)
        return articleEntities.map { articleObjectMapper.toArticle(it) }
    }

    override fun getArticleByKeyword(keyword: String): List<Article> {
        val articleEntities = articleRepository.findByKeyword(keyword)
        return articleEntities.map { articleObjectMapper.toArticle(it) }
    }

    override fun getArticleByPeriod(from: Date, to: Date): List<Article> {
        val articleEntities = articleRepository.findByPeriod(from, to)
        return articleEntities.map { articleObjectMapper.toArticle(it) }
    }

    override fun createArticle(article: Article): Article {
        if(doesArticleExist(article)) {
            throw ArticleCreationException("Unable to create article. An article with article id ${article.articleId} already exist")
        }
        val requestArticleEntity = articleObjectMapper.fromArticle(article)
        return try {
            val response: ArticleEntity = articleRepository.save(requestArticleEntity)
            return articleObjectMapper.toArticle(response)
        } catch (e: RuntimeException) {
            throw ArticleCreationException("Problem occurred when trying to create article")
        }

    }

    override fun updateArticle(article: Article): Article {
        val requestArticleEntity = articleObjectMapper.fromArticle(article)
        return try {
            articleObjectMapper.toArticle(articleRepository.save(requestArticleEntity))
        } catch (e: RuntimeException) {
            throw ArticleCreationException("Problem occurred when trying to update article")
        }
    }

    private fun doesArticleExist(article: Article) = articleRepository.existsById(article.articleId)

    override fun deleteArticle(articleId: Long) {
        try {
            articleRepository.deleteById(articleId)
        } catch (e: RuntimeException) {
            throw ArticleNotFoundException("Unable to delete article. An article with article id $articleId was not found")
        }
    }

}