package com.upday.newsarticle.entity

import java.sql.Date
import javax.persistence.*

@Entity(name = "article")
@Table(name = "article")
data class ArticleEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "article_id")
        val articleId: Long,
        @Column(nullable = true)
        val header: String?,
        @Column(name = "short_description", nullable = true)
        val shortDescription: String?,
        val text: String?,
        @Column(name = "publish_date")
        val publishDate: Date,
        @ManyToOne
        @JoinColumn(name = "author_id")
        val author: AuthorEntity,
        @ElementCollection
        @Column(name = "keywords", nullable = true)
        val keywords: MutableSet<String>?
)