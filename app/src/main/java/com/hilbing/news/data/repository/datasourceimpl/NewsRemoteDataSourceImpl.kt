package com.hilbing.news.data.repository.datasourceimpl

import com.hilbing.news.data.api.NewsAPIService
import com.hilbing.news.data.model.APIResponse
import com.hilbing.news.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService

): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country, page)
    }
}