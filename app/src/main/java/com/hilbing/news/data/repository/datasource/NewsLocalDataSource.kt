package com.hilbing.news.data.repository.datasource

import com.hilbing.news.data.model.Article

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)
}