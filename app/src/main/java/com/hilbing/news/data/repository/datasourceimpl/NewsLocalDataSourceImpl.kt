package com.hilbing.news.data.repository.datasourceimpl

import com.hilbing.news.data.db.ArticleDAO
import com.hilbing.news.data.model.Article
import com.hilbing.news.data.repository.datasource.NewsLocalDataSource

class NewsLocalDataSourceImpl (
    private val articleDAO: ArticleDAO
        ): NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }
}