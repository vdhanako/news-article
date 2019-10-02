package com.upday.newsarticle.features;

import com.upday.newsarticle.AcceptanceTestBase;
import com.upday.newsarticle.steps.NewsArticleUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;

@RunWith(SerenityRunner.class)
public class NewsArticleUserFeature extends AcceptanceTestBase {

    @Steps
    private NewsArticleUserSteps newsArticleUserSteps;

    @Test
    @Title("A user should be able to view all articles")
    public void a_user_should_be_able_to_view_all_articles() {
        String requestUrl = String.format(getBaseUrl(), "article");
        newsArticleUserSteps.given_a_user_has_get_all_articles_endpoint(requestUrl);
        newsArticleUserSteps.when_user_tries_to_get_all_articles(requestUrl);
        newsArticleUserSteps.then_user_should_be_able_to_view_all_articles_successfully();
    }

    @Test
    @Title("A user should be able to see one article for a given article")
    public void a_user_should_be_able_to_see_one_article_for_a_given_article() {
        String requestUrl = String.format(getBaseUrl(), "article/1");
        newsArticleUserSteps.given_a_user_has_a_get_article_for_article_id_endpoint(requestUrl);
        newsArticleUserSteps.when_user_tries_to_get_an_article_for_article_id(requestUrl, "1");
        newsArticleUserSteps.then_user_should_be_able_to_view_that_article_successfully();
    }

    @Test
    @Title("A user should be able to view all article for a given author")
    public void a_user_should_be_able_to_view_all_articles_for_a_given_author() {
        String requestUrl = String.format(getBaseUrl(), "article/author/1");
        newsArticleUserSteps.given_a_user_has_a_get_article_for_author_id_endpoint(requestUrl);
        newsArticleUserSteps.when_user_tries_to_get_all_articles_for_an_author(requestUrl, "1");
        newsArticleUserSteps.then_user_should_be_able_to_view_all_articles_for_the_author_successfully();
    }

    @Test
    @Title("A user should be able to view all article for a specific keyword")
    public void a_user_should_be_able_to_view_all_articles_for_a_specific_keyword() {
        String requestUrl = String.format(getBaseUrl(), "article/author/1");
        newsArticleUserSteps.given_a_user_has_a_get_article_for_keyword_endpoint(requestUrl);
        newsArticleUserSteps.when_user_tries_to_get_all_articles_for_the_keyword(requestUrl, "some");
        newsArticleUserSteps.then_user_should_be_able_to_view_all_articles_with_that_keyword_successfully();
    }

    @Test
    @Title("A user should be able to view all article for a given period")
    public void a_user_should_be_able_to_view_all_articles_for_a_given_period() {
        String requestUrl = String.format(getBaseUrl(), "article/date/from/2019-10-02/to/2019-10-03");
        newsArticleUserSteps.given_a_user_has_a_get_between_a_period_endpoint(requestUrl);
        newsArticleUserSteps.when_user_tries_to_get_all_articles_for_the_dates(requestUrl, "2019-10-02", "2019-10-03");
        newsArticleUserSteps.then_user_should_be_able_to_view_all_articles_between_those_dates_successfully();
    }
}