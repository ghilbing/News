package com.hilbing.news.domain.usecase

import com.hilbing.news.data.model.APIResponse
import com.hilbing.news.data.util.Resource
import com.hilbing.news.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse>{
        return newsRepository.getNewsHeadlines(country, page)
    }
}