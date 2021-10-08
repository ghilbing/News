package com.hilbing.news.domain.usecase

import com.hilbing.news.data.model.APIResponse
import com.hilbing.news.data.util.Resource
import com.hilbing.news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String): Resource<APIResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}