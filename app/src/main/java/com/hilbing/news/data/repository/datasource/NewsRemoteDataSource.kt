package com.hilbing.news.data.repository.datasource

import com.hilbing.news.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
    suspend fun getSearchedTopHeadlines(country: String, searchQuery: String, page: Int): Response<APIResponse>
}