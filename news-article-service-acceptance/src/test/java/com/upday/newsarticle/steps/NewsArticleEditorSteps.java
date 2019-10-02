package com.upday.newsarticle.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NewsArticleEditorSteps {

    @Step("Given an editor has a create new article endpoint")
    public String given_an_editor_has_a_create_new_article_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When editor tries to create a new article")
    public Response when_editor_tries_to_create_a_new_article(String requestUrl, String requestBody) {
        return rest().when().header("content-type", "application/json").body(requestBody).post(requestUrl);
    }

    @Step("Then editor should be able to create that article successfully")
    public void then_editor_should_be_able_to_create_that_article_successfully() {
        then().assertThat().statusCode(is(equalTo (200)));
        then().assertThat().body("articleId", is(equalTo(4)));
        then().assertThat().body("header", is(equalTo("some header")));
        then().assertThat().body("shortDescription", is(equalTo("some description")));
        then().assertThat().body("text", is(equalTo("some text")));
        then().assertThat().body("publishDate", is(equalTo("2019-10-01")));
        then().assertThat().body("author.authorId", is(equalTo(1)));
        then().assertThat().body("author.authorName", is(equalTo("venkat")));
        then().assertThat().body("keywords[0]", is(equalTo("some")));
    }


    @Step("Given an editor has a update existing article endpoint")
    public String given_an_editor_has_a_update_existing_article_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When editor tries to update existing article id {1}")
    public Response when_editor_tries_to_update_existing_article(String requestUrl, String requestBody) {
        return rest().when().header("content-type", "application/json").body(requestBody).put(requestUrl);
    }

    @Step("Then editor should be able to update that article successfully")
    public void then_editor_should_be_able_to_update_that_article_successfully() {
        then().assertThat().statusCode(is(equalTo (200)));
        then().assertThat().body("articleId", is(equalTo(2)));
        then().assertThat().body("header", is(equalTo("some header modified")));
    }


    @Step("Given an editor has a delete existing article endpoint")
    public String given_an_editor_has_a_delete_existing_article_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When editor tries to delete existing article id {1}")
    public Response when_editor_tries_to_delete_existing_article(String requestUrl, String articleId) {
        return rest().when().delete(requestUrl);
    }

    @Step("Then editor should be able to delete that article successfully")
    public void then_editor_should_be_able_to_delete_that_article_successfully() {
        then().assertThat().statusCode(is(equalTo (200)));
    }

}