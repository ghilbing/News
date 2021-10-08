package com.hilbing.news.presentation.di

import com.hilbing.news.data.api.NewsAPIService
import com.hilbing.news.data.repository.datasource.NewsRemoteDataSource
import com.hilbing.news.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}