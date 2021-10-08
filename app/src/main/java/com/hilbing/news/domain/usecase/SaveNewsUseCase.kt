package com.hilbing.news.domain.usecase

import com.hilbing.news.data.model.Article
import com.hilbing.news.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}