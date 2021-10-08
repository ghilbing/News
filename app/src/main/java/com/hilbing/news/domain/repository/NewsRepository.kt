package com.hilbing.news.domain.repository

import androidx.lifecycle.LiveData
import com.hilbing.news.data.model.APIResponse
import com.hilbing.news.data.model.Article
import com.hilbing.news.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery: String) : Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>


}