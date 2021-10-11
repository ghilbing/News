package com.hilbing.news.presentation.di

import android.app.Application
import com.hilbing.news.domain.usecase.GetNewsHeadlinesUseCase
import com.hilbing.news.domain.usecase.GetSearchedNewsUseCase
import com.hilbing.news.domain.usecase.SaveNewsUseCase
import com.hilbing.news.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase
    ): NewsViewModelFactory{
        return NewsViewModelFactory(application, getNewsHeadlinesUseCase, getSearchedNewsUseCase, saveNewsUseCase)
    }
}