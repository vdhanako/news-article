package com.upday.newsarticle.steps

import io.restassured.response.Response
import net.serenitybdd.rest.SerenityRest.rest
import net.serenitybdd.rest.SerenityRest.then
import net.thucydides.core.annotations.Step
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo

class NewsArticleSteps {

    @Step("Given a user has get all articles endpoint")
    fun given_a_user_has_get_all_articles_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When user tries to get all articles")
    fun when_user_tries_to_get_all_articles(requestUrl: String): Response {
        return rest().`when`().get(requestUrl)
    }

    @Step("Then user should be able to view all articles successfully")
    fun then_user_should_be_able_to_view_all_articles_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("[0].articleId", `is`(equalTo(1)))
        then().assertThat().body("[0].header", `is`(equalTo("some header")))
        then().assertThat().body("[0].shortDescription", `is`(equalTo("some description")))
        then().assertThat().body("[0].text", `is`(equalTo("some text")))
        then().assertThat().body("[0].publishDate", `is`(equalTo("2019-10-01")))
        then().assertThat().body("[0].author.authorId", `is`(equalTo(1)))
        then().assertThat().body("[0].author.authorName", `is`(equalTo("venkat")))
        then().assertThat().body("[0].keywords[0]", `is`(equalTo("some")))
    }

    @Step("Given a user has a get article for article id endpoint")
    fun given_a_user_has_a_get_article_for_article_id_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When the user tries to get an article for article id {1}")
    fun when_user_tries_to_get_an_article_for_article_id(requestUrl: String, articleId: String): Response {
        return rest().`when`().get(requestUrl)
    }

    @Step("Then user should be able to view that article successfully")
    fun then_user_should_be_able_to_view_that_article_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("articleId", `is`(equalTo(1)))
        then().assertThat().body("header", `is`(equalTo("some header")))
        then().assertThat().body("shortDescription", `is`(equalTo("some description")))
        then().assertThat().body("text", `is`(equalTo("some text")))
        then().assertThat().body("publishDate", `is`(equalTo("2019-10-01")))
        then().assertThat().body("author.authorId", `is`(equalTo(1)))
        then().assertThat().body("author.authorName", `is`(equalTo("venkat")))
        then().assertThat().body("keywords[0]", `is`(equalTo("some")))
    }

    @Step("Given a user has a get article for author id endpoint")
    fun given_a_user_has_a_get_article_for_author_id_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When user tries to get all articles for author id {1}")
    fun when_user_tries_to_get_all_articles_for_an_author(requestUrl: String, authorId: String): Response {
        return rest().`when`().get(requestUrl)
    }

    @Step("Then user should be able to view all articles for the author successfully")
    fun then_user_should_be_able_to_view_all_articles_for_the_author_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("[0].articleId", `is`(equalTo(1)))
        then().assertThat().body("[0].header", `is`(equalTo("some header")))
        then().assertThat().body("[0].shortDescription", `is`(equalTo("some description")))
        then().assertThat().body("[0].text", `is`(equalTo("some text")))
        then().assertThat().body("[0].publishDate", `is`(equalTo("2019-10-01")))
        then().assertThat().body("[0].author.authorId", `is`(equalTo(1)))
        then().assertThat().body("[0].author.authorName", `is`(equalTo("venkat")))
        then().assertThat().body("[0].keywords[0]", `is`(equalTo("some")))
    }

    @Step("Given a user has a get article for keyword endpoint")
    fun given_a_user_has_a_get_article_for_keyword_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When user tries to get all articles for the keyword {1}")
    fun when_user_tries_to_get_all_articles_for_the_keyword(requestUrl: String, authorId: String): Response {
        return rest().`when`().get(requestUrl)
    }

    @Step("Then user should be able to view all articles with that keyword successfully")
    fun then_user_should_be_able_to_view_all_articles_with_that_keyword_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("[0].articleId", `is`(equalTo(1)))
        then().assertThat().body("[0].header", `is`(equalTo("some header")))
        then().assertThat().body("[0].shortDescription", `is`(equalTo("some description")))
        then().assertThat().body("[0].text", `is`(equalTo("some text")))
        then().assertThat().body("[0].publishDate", `is`(equalTo("2019-10-01")))
        then().assertThat().body("[0].author.authorId", `is`(equalTo(1)))
        then().assertThat().body("[0].author.authorName", `is`(equalTo("venkat")))
        then().assertThat().body("[0].keywords[0]", `is`(equalTo("some")))
    }

    @Step("Given an editor has a create new article endpoint")
    fun given_an_editor_has_a_create_new_article_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When editor tries to create a new article")
    fun when_editor_tries_to_create_a_new_article(requestUrl: String, requestBody: String): Response {
        return rest().`when`().header("content-type", "application/json").body(requestBody).post(requestUrl)
    }

    @Step("Then editor should be able to create that article successfully")
    fun then_editor_should_be_able_to_create_that_article_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("articleId", `is`(equalTo(4)))
        then().assertThat().body("header", `is`(equalTo("some header")))
        then().assertThat().body("shortDescription", `is`(equalTo("some description")))
        then().assertThat().body("text", `is`(equalTo("some text")))
        then().assertThat().body("publishDate", `is`(equalTo("2019-10-01")))
        then().assertThat().body("author.authorId", `is`(equalTo(1)))
        then().assertThat().body("author.authorName", `is`(equalTo("venkat")))
        then().assertThat().body("keywords[0]", `is`(equalTo("some")))
    }


    @Step("Given an editor has a update existing article endpoint")
    fun given_an_editor_has_a_update_existing_article_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When editor tries to update existing article id {1}")
    fun when_editor_tries_to_update_existing_article(requestUrl: String, requestBody: String): Response {
        return rest().`when`().header("content-type", "application/json").body(requestBody).post(requestUrl)
    }

    @Step("Then editor should be able to update that article successfully")
    fun then_editor_should_be_able_to_update_that_article_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
        then().assertThat().body("articleId", `is`(equalTo(2)))
        then().assertThat().body("header", `is`(equalTo("some header modified")))
    }


    @Step("Given an editor has a delete existing article endpoint")
    fun given_an_editor_has_a_delete_existing_article_endpoint(requestUrl: String): String {
        return requestUrl
    }

    @Step("When editor tries to delete existing article id {1}")
    fun when_editor_tries_to_delete_existing_article(requestUrl: String, articleId: String): Response {
        return rest().`when`().delete(requestUrl)
    }

    @Step("Then editor should be able to delete that article successfully")
    fun then_editor_should_be_able_to_delete_that_article_successfully() {
        then().assertThat().statusCode(`is`<Int>(equalTo<Int>(200)))
    }

}