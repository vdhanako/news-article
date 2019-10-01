package com.upday.newsarticle.controller

import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.service.api.NewsArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/article")
class NewsArticleController {

    @Autowired
    lateinit var newsArticleService: NewsArticleService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAllArticles() : ResponseEntity<List<Article>> {
        var articles = newsArticleService.getAllArticles()
        return ResponseEntity.ok(articles)
    }

    @GetMapping("/{articleId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getArticle(@PathVariable("articleId") articleId: Long) : ResponseEntity<Article> {
        var article = newsArticleService.getArticle(articleId)
        return ResponseEntity.ok(article)
    }

    @GetMapping("/author/{authorId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getArticleByAuthor(@PathVariable("authorId") authorId: Long) : ResponseEntity<List<Article>> {
        var articles = newsArticleService.getArticleByAuthor(authorId)
        return ResponseEntity.ok(articles)
    }

    @GetMapping("/keyword/{keyword}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getArticleByKeyword(@PathVariable("keyword") keyword: String) : ResponseEntity<List<Article>> {
        var articles = newsArticleService.getArticleByKeyword(keyword)
        return ResponseEntity.ok(articles)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createArticle(@RequestBody article: Article) : ResponseEntity<Article> {
        var article = newsArticleService.createArticle(article)
        return ResponseEntity.ok(article)
    }

    @DeleteMapping("/{articleId}")
    fun deleteArticle(@PathVariable("articleId") articleId: Long) : ResponseEntity<Any> {
        newsArticleService.deleteArticle(articleId)
        return ResponseEntity.ok().build()
    }

}