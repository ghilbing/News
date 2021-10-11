package com.hilbing.news.presentation.di

import com.hilbing.news.data.db.ArticleDAO
import com.hilbing.news.data.repository.datasource.NewsLocalDataSource
import com.hilbing.news.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}