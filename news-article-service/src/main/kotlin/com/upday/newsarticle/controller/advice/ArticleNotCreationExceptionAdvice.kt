package com.upday.newsarticle.controller.advice

import com.upday.newsarticle.domain.ErrorMessage
import com.upday.newsarticle.exception.ArticleCreationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ArticleNotCreationExceptionAdvice {

    @ExceptionHandler(ArticleCreationException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun articleNotCreated(ex: ArticleCreationException): ResponseEntity<*> {
        val response = ErrorMessage(ex.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

}