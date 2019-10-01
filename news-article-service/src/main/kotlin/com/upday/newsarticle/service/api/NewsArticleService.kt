package com.upday.newsarticle.service.api

import com.upday.newsarticle.domain.Article

interface NewsArticleService {

    fun getAllArticles() : List<Article>

    fun getArticle(articleId: Long) : Article

    fun getArticleByAuthor(authorId: Long) : List<Article>

    fun getArticleByKeyword(keyword: String) : List<Article>

    fun createArticle(article: Article) : Article

    fun deleteArticle(articleId: Long)

}