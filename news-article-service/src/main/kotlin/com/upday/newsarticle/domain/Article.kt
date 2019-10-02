package com.upday.newsarticle.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Date

@JsonIgnoreProperties
data class Article(
        val articleId: Long,
        val header: String?,
        val shortDescription: String?,
        val text: String?,
        val publishDate: Date,
        val author: Author,
        val keywords: MutableSet<String>?
)