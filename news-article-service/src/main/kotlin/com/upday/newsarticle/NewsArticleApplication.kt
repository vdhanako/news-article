package com.upday.newsarticle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsArticleApplication

fun main(args: Array<String>) {
	runApplication<NewsArticleApplication>(*args)
}
