package com.upday.newsarticle.repository

import com.upday.newsarticle.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.sql.Date

@Repository
interface ArticleRepository : JpaRepository<ArticleEntity, Long> {

    @Query("select art from article art where art.author.authorId = :authorId")
    fun findByAuthor(@Param("authorId") authorId: Long): MutableList<ArticleEntity>

    @Query("select art from article art join art.keywords k where k = :keyword")
    fun findByKeyword(@Param("keyword") keyword: String): MutableList<ArticleEntity>

    @Query("select art FROM article art where art.publishDate between :from and :to")
    fun findByPeriod(@Param("from") from: Date, @Param("to") to: Date): MutableList<ArticleEntity>

}