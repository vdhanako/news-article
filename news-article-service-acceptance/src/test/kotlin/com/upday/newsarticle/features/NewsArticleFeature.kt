package com.upday.newsarticle.features

import com.upday.newsarticle.AcceptanceTestsBase
import com.upday.newsarticle.steps.NewsArticleSteps
import net.serenitybdd.junit.runners.SerenityRunner
import net.thucydides.core.annotations.Steps
import net.thucydides.core.annotations.Title
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(SerenityRunner::class)
class NewsArticleFeature : AcceptanceTestsBase() {

    @Steps
    private val newsArticleSteps = NewsArticleSteps()

    @Test
    @Title("A user should be able to view all articles")
    fun a_user_should_be_able_to_view_all_articles() {
        val requestUrl = String.format(getBaseUrl(), "article")
        newsArticleSteps.given_a_user_has_get_all_articles_endpoint(requestUrl)
        newsArticleSteps.when_user_tries_to_get_all_articles(requestUrl)
        newsArticleSteps.then_user_should_be_able_to_view_all_articles_successfully()
    }

    @Test
    @Title("A user should be able to see one article for a given article")
    fun a_user_should_be_able_to_see_one_article_for_a_given_article() {
        val requestUrl = String.format(getBaseUrl(), "article/1")
        newsArticleSteps.given_a_user_has_a_get_article_for_article_id_endpoint(requestUrl)
        newsArticleSteps.when_user_tries_to_get_an_article_for_article_id(requestUrl, "1")
        newsArticleSteps.then_user_should_be_able_to_view_that_article_successfully()
    }

    @Test
    @Title("A user should be able to see view all article for a given author")
    fun a_user_should_be_able_to_view_all_articles_for_a_given_author() {
        val requestUrl = String.format(getBaseUrl(), "article/author/1")
        newsArticleSteps.given_a_user_has_a_get_article_for_author_id_endpoint(requestUrl)
        newsArticleSteps.when_user_tries_to_get_all_articles_for_an_author(requestUrl, "1")
        newsArticleSteps.then_user_should_be_able_to_view_all_articles_for_the_author_successfully()
    }

    @Test
    @Title("A user should be able to see view all article for a specific keyword")
    fun a_user_should_be_able_to_view_all_articles_for_a_specific_keyword() {
        val requestUrl = String.format(getBaseUrl(), "article/author/1")
        newsArticleSteps.given_a_user_has_a_get_article_for_keyword_endpoint(requestUrl)
        newsArticleSteps.when_user_tries_to_get_all_articles_for_the_keyword(requestUrl, "some")
        newsArticleSteps.then_user_should_be_able_to_view_all_articles_with_that_keyword_successfully()
    }

    @Test
    @Title("An editor should be able to create a new article")
    fun an_editor_should_be_able_to_create_a_new_article() {
        val requestUrl = String.format(getBaseUrl(), "article")
        newsArticleSteps.given_an_editor_has_a_create_new_article_endpoint(requestUrl)
        newsArticleSteps.when_editor_tries_to_create_a_new_article(requestUrl, "{\"header\":\"some header\",\"shortDescription\":\"some description\",\"text\":\"some text\",\"publishDate\":\"2019-10-01\",\"author\":{\"authorId\":1,\"authorName\":\"venkat\"},\"keywords\":[\"some\"]}")
        newsArticleSteps.then_editor_should_be_able_to_create_that_article_successfully()
    }

    @Test
    @Title("An editor should be able to update existing article")
    fun an_editor_should_be_able_to_update_existing_article() {
        val requestUrl = String.format(getBaseUrl(), "article")
        newsArticleSteps.given_an_editor_has_a_update_existing_article_endpoint(requestUrl)
        newsArticleSteps.when_editor_tries_to_update_existing_article(requestUrl, "{\"articleId\":2,\"header\":\"some header modified\",\"shortDescription\":\"some description\",\"text\":\"some text\",\"publishDate\":\"2019-10-01\",\"author\":{\"authorId\":1,\"authorName\":\"venkat\"},\"keywords\":[\"some\"]}")
        newsArticleSteps.then_editor_should_be_able_to_update_that_article_successfully()
    }

    @Test
    @Title("An editor should be able to delete existing article")
    fun an_editor_should_be_able_to_delete_existing_article() {
        val requestUrl = String.format(getBaseUrl(), "article/3")
        newsArticleSteps.given_an_editor_has_a_delete_existing_article_endpoint(requestUrl)
        newsArticleSteps.when_editor_tries_to_delete_existing_article(requestUrl, "3")
        newsArticleSteps.then_editor_should_be_able_to_delete_that_article_successfully()
    }

}