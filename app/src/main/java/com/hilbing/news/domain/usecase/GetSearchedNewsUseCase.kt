package com.hilbing.news.domain.usecase

import com.hilbing.news.data.model.APIResponse
import com.hilbing.news.data.util.Resource
import com.hilbing.news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<APIResponse>{
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}