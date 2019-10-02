package com.upday.newsarticle.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NewsArticleUserSteps {

    @Step("Given a user has get all articles endpoint")
    public String given_a_user_has_get_all_articles_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When user tries to get all articles")
    public Response when_user_tries_to_get_all_articles(String requestUrl) {
        return rest().when().get(requestUrl);
    }

    @Step("Then user should be able to view all articles successfully")
    public void then_user_should_be_able_to_view_all_articles_successfully() {
        then().assertThat().statusCode(is(equalTo(200)));
        then().assertThat().body("[0].articleId", is(equalTo(1)));
        then().assertThat().body("[0].header", is(equalTo("some header")));
        then().assertThat().body("[0].shortDescription", is(equalTo("some description")));
        then().assertThat().body("[0].text", is(equalTo("some text")));
        then().assertThat().body("[0].publishDate", is(equalTo("2019-10-01")));
        then().assertThat().body("[0].author.authorId", is(equalTo(1)));
        then().assertThat().body("[0].author.authorName", is(equalTo("venkat")));
        then().assertThat().body("[0].keywords[0]", is(equalTo("some")));
    }

    @Step("Given a user has a get article for article id endpoint")
    public String given_a_user_has_a_get_article_for_article_id_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When the user tries to get an article for article id {1}")
    public Response when_user_tries_to_get_an_article_for_article_id(String requestUrl, String articleId) {
        return rest().when().get(requestUrl);
    }

    @Step("Then user should be able to view that article successfully")
    public void then_user_should_be_able_to_view_that_article_successfully() {
        then().assertThat().statusCode(is(equalTo(200)));
        then().assertThat().body("articleId", is(equalTo(1)));
        then().assertThat().body("header", is(equalTo("some header")));
        then().assertThat().body("shortDescription", is(equalTo("some description")));
        then().assertThat().body("text", is(equalTo("some text")));
        then().assertThat().body("publishDate", is(equalTo("2019-10-01")));
        then().assertThat().body("author.authorId", is(equalTo(1)));
        then().assertThat().body("author.authorName", is(equalTo("venkat")));
        then().assertThat().body("keywords[0]", is(equalTo("some")));
    }

    @Step("Given a user has a get article for author id endpoint")
    public String given_a_user_has_a_get_article_for_author_id_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When user tries to get all articles for author id {1}")
    public Response when_user_tries_to_get_all_articles_for_an_author(String requestUrl, String authorId) {
        return rest().when().get(requestUrl);
    }

    @Step("Then user should be able to view all articles for the author successfully")
    public void then_user_should_be_able_to_view_all_articles_for_the_author_successfully() {
        then().assertThat().statusCode(is(equalTo(200)));
        then().assertThat().body("[0].articleId", is(equalTo(1)));
        then().assertThat().body("[0].header", is(equalTo("some header")));
        then().assertThat().body("[0].shortDescription", is(equalTo("some description")));
        then().assertThat().body("[0].text", is(equalTo("some text")));
        then().assertThat().body("[0].publishDate", is(equalTo("2019-10-01")));
        then().assertThat().body("[0].author.authorId", is(equalTo(1)));
        then().assertThat().body("[0].author.authorName", is(equalTo("venkat")));
        then().assertThat().body("[0].keywords[0]", is(equalTo("some")));
    }

    @Step("Given a user has a get article for keyword endpoint")
    public String given_a_user_has_a_get_article_for_keyword_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When user tries to get all articles for the keyword {1}")
    public Response when_user_tries_to_get_all_articles_for_the_keyword(String requestUrl, String authorId) {
        return rest().when().get(requestUrl);
    }

    @Step("Then user should be able to view all articles with that keyword successfully")
    public void then_user_should_be_able_to_view_all_articles_with_that_keyword_successfully() {
        then().assertThat().statusCode(is(equalTo (200)));
        then().assertThat().body("[0].articleId", is(equalTo(1)));
        then().assertThat().body("[0].header", is(equalTo("some header")));
        then().assertThat().body("[0].shortDescription", is(equalTo("some description")));
        then().assertThat().body("[0].text", is(equalTo("some text")));
        then().assertThat().body("[0].publishDate", is(equalTo("2019-10-01")));
        then().assertThat().body("[0].author.authorId", is(equalTo(1)));
        then().assertThat().body("[0].author.authorName", is(equalTo("venkat")));
        then().assertThat().body("[0].keywords[0]", is(equalTo("some")));
    }

    @Step("Given a user has a get article between a period endpoint")
    public String given_a_user_has_a_get_between_a_period_endpoint(String requestUrl) {
        return requestUrl;
    }

    @Step("When user tries to get all articles between that period {1} and {2}")
    public Response when_user_tries_to_get_all_articles_for_the_dates(String requestUrl, String from, String to) {
        return rest().when().get(requestUrl);
    }

    @Step("Then user should be able to view all articles between those dates successfully")
    public void then_user_should_be_able_to_view_all_articles_between_those_dates_successfully() {
        then().assertThat().statusCode(is(equalTo (200)));
        then().assertThat().body("[0].articleId", is(equalTo(3)));
        then().assertThat().body("[0].publishDate", is(equalTo("2019-10-02")));
    }

}