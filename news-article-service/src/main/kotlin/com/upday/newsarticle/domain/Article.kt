package com.upday.newsarticle.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Date

@JsonIgnoreProperties
data class Article(
        var articleId: Long,
        var header: String?,
        var shortDescription: String?,
        var text: String?,
        var publishDate: Date,
        var author: Author,
        var keywords: MutableSet<String>?
)