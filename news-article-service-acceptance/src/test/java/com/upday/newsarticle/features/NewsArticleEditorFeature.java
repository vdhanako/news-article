package com.upday.newsarticle.features;

import com.upday.newsarticle.AcceptanceTestBase;
import com.upday.newsarticle.steps.NewsArticleEditorSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class NewsArticleEditorFeature extends AcceptanceTestBase {

    @Steps
    private NewsArticleEditorSteps newsArticleEditorSteps;

    @Test
    @Title("An editor should be able to create a new article")
    public void an_editor_should_be_able_to_create_a_new_article() {
        String requestUrl = String.format(getBaseUrl(), "article");
        newsArticleEditorSteps.given_an_editor_has_a_create_new_article_endpoint(requestUrl);
        newsArticleEditorSteps.when_editor_tries_to_create_a_new_article(requestUrl, "{\"header\":\"some header\",\"shortDescription\":\"some description\",\"text\":\"some text\",\"publishDate\":\"2019-10-01\",\"author\":{\"authorId\":1,\"authorName\":\"venkat\"},\"keywords\":[\"some\"]}");
        newsArticleEditorSteps.then_editor_should_be_able_to_create_that_article_successfully();
    }

    @Test
    @Title("An editor should be able to update existing article")
    public void an_editor_should_be_able_to_update_existing_article() {
        String requestUrl = String.format(getBaseUrl(), "article");
        newsArticleEditorSteps.given_an_editor_has_a_update_existing_article_endpoint(requestUrl);
        newsArticleEditorSteps.when_editor_tries_to_update_existing_article(requestUrl, "{\"articleId\":2,\"header\":\"some header modified\",\"shortDescription\":\"some description\",\"text\":\"some text\",\"publishDate\":\"2019-10-01\",\"author\":{\"authorId\":1,\"authorName\":\"venkat\"},\"keywords\":[\"some\"]}");
        newsArticleEditorSteps.then_editor_should_be_able_to_update_that_article_successfully();
    }

    @Test
    @Title("An editor should be able to delete existing article")
    public void an_editor_should_be_able_to_delete_existing_article() {
        String requestUrl = String.format(getBaseUrl(), "article/3");
        newsArticleEditorSteps.given_an_editor_has_a_delete_existing_article_endpoint(requestUrl);
        newsArticleEditorSteps.when_editor_tries_to_delete_existing_article(requestUrl, "3");
        newsArticleEditorSteps.then_editor_should_be_able_to_delete_that_article_successfully();
    }

}