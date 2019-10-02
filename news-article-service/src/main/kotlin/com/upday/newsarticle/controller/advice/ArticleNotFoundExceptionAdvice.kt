package com.upday.newsarticle.controller.advice

import com.upday.newsarticle.domain.ErrorMessage
import com.upday.newsarticle.exception.ArticleNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ArticleNotFoundExceptionAdvice {

    @ExceptionHandler(ArticleNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun articleNotFound(ex: ArticleNotFoundException): ResponseEntity<ErrorMessage> {
        val response = ErrorMessage(ex.message)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

}