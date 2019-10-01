package com.upday.newsarticle.entity

import java.sql.Date
import javax.persistence.*

@Entity(name = "article")
@Table(name = "article")
data class ArticleEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "article_id")
        var articleId: Long,
        @Column(nullable = true)
        var header: String?,
        @Column(name = "short_description", nullable = true)
        var shortDescription: String?,
        var text: String?,
        @Column(name = "publish_date")
        var publishDate: Date,
        @ManyToOne
        @JoinColumn(name = "author_id")
        var author: AuthorEntity,
        @ElementCollection
        @Column(name = "keywords", nullable = true)
        var keywords: MutableSet<String>?
)