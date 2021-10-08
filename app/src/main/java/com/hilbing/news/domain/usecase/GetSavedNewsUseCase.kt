package com.hilbing.news.domain.usecase

import com.hilbing.news.data.model.Article
import com.hilbing.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute() : Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}