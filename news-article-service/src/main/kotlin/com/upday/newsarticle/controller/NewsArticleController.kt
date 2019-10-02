package com.upday.newsarticle.controller

import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.service.api.NewsArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/article")
class NewsArticleController {

    @Autowired
    lateinit var newsArticleService: NewsArticleService

    @GetMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getAllArticles() : ResponseEntity<List<Article>> {
        val response = newsArticleService.getAllArticles()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{articleId}", produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getArticle(@PathVariable("articleId") articleId: Long) : ResponseEntity<Article> {
        val response = newsArticleService.getArticle(articleId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/author/{authorId}", produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getArticleByAuthor(@PathVariable("authorId") authorId: Long) : ResponseEntity<List<Article>> {
        val response = newsArticleService.getArticleByAuthor(authorId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/keyword/{keyword}", produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getArticleByKeyword(@PathVariable("keyword") keyword: String) : ResponseEntity<List<Article>> {
        val response = newsArticleService.getArticleByKeyword(keyword)
        return ResponseEntity.ok(response)
    }

    @PostMapping(consumes = [APPLICATION_JSON_UTF8_VALUE], produces = [APPLICATION_JSON_UTF8_VALUE])
    fun createArticle(@RequestBody article: Article) : ResponseEntity<Article> {
        val response = newsArticleService.createArticle(article)
        return ResponseEntity.ok(response)
    }

    @PutMapping(consumes = [APPLICATION_JSON_UTF8_VALUE], produces = [APPLICATION_JSON_UTF8_VALUE])
    fun updateArticle(@RequestBody article: Article) : ResponseEntity<Article> {
        val response = newsArticleService.updateArticle(article)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{articleId}")
    fun deleteArticle(@PathVariable("articleId") articleId: Long) : ResponseEntity<Any> {
        newsArticleService.deleteArticle(articleId)
        return ResponseEntity.ok().build()
    }

}