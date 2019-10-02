package com.upday.newsarticle.mapper

import com.upday.newsarticle.domain.Article
import com.upday.newsarticle.domain.Author
import com.upday.newsarticle.entity.ArticleEntity
import com.upday.newsarticle.entity.AuthorEntity
import org.springframework.stereotype.Component

@Component
class ArticleObjectMapper {

    fun toArticle(articleEntity: ArticleEntity) : Article {
        val author = Author(articleEntity.author.authorId, articleEntity.author.authorName)

        return Article(articleEntity.articleId, articleEntity.header, articleEntity.shortDescription,
                articleEntity.text, articleEntity.publishDate, author, articleEntity.keywords)
    }

    fun fromArticle(article: Article) : ArticleEntity {
        val authorEntity = AuthorEntity(article.author.authorId, article.author.authorName)

        return ArticleEntity(article.articleId, article.header, article.shortDescription,
                article.text, article.publishDate, authorEntity, article.keywords)
    }

}