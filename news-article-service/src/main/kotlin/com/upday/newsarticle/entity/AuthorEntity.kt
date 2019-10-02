package com.upday.newsarticle.entity

import javax.persistence.*

@Entity(name = "author")
@Table(name = "author")
data class AuthorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "author_id")
        val authorId: Long,
        @Column(name = "author_name")
        val authorName: String

)