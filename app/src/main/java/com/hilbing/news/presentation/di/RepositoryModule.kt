package com.hilbing.news.presentation.di

import com.hilbing.news.data.repository.NewsRepositoryImpl
import com.hilbing.news.data.repository.datasource.NewsLocalDataSource
import com.hilbing.news.data.repository.datasource.NewsRemoteDataSource
import com.hilbing.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}