package com.upday.newsarticle.service.impl

import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.domain.Author
import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.entity.AuthorEntity
import com.upday.newsarticle.exception.ArticleNotCreatedException
import com.upday.newsarticle.exception.ArticleNotFoundException
import com.upday.newsarticle.repository.ArticleRepository
import com.upday.newsarticle.service.api.NewsArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class NewsArticleServiceImpl : NewsArticleService {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    override fun getAllArticles(): List<Article> {
        var articleEntities = articleRepository.findAll()
        return articleEntities.map { convertArticleEntity(it) }.toList()
    }

    override fun getArticle(articleId: Long): Article {
        var articleEntity = articleRepository.findById(articleId)
        if(!articleEntity.isPresent()) {
            throw ArticleNotFoundException("article with $articleId was not found")
        }
        return convertArticleEntity(articleEntity.get())
    }

    override fun getArticleByAuthor(authorId: Long): List<Article> {
        var articleEntities = articleRepository.findByAuthor(authorId)
        return articleEntities.map { convertArticleEntity(it) }.toList()
    }

    override fun getArticleByKeyword(keyword: String): List<Article> {
        var articleEntities = articleRepository.findByKeyword(keyword)
        return articleEntities.map { convertArticleEntity(it) }.toList()
    }

    override fun createArticle(article: Article): Article {
        var requestArticleEntity = convertArticle(article)
        var response = Optional.of(articleRepository.save(requestArticleEntity))
        if(!response.isPresent) {
            throw ArticleNotCreatedException("problem occurred when trying to create article")
        }
        return convertArticleEntity(response.get());
    }

    override fun deleteArticle(articleId: Long) {
        var isArticleExist = articleRepository.existsById(articleId)
        if(!isArticleExist) {
            throw ArticleNotFoundException("article with $articleId was not found")
        }
        articleRepository.deleteById(articleId)
    }

    private fun convertArticleEntity(articleEntity: ArticleEntity) : Article {
        var author = Author(articleEntity.author.authorId, articleEntity.author.authorName)

        return Article(articleEntity.articleId, articleEntity.header, articleEntity.shortDescription,
                articleEntity.text, articleEntity.publishDate, author, articleEntity.keywords)
    }

    private fun convertArticle(article: Article) : ArticleEntity {
        var authorEntity = AuthorEntity(article.author.authorId, article.author.authorName)

        return ArticleEntity(article.articleId, article.header, article.shortDescription,
                article.text, article.publishDate, authorEntity, article.keywords)
    }

}