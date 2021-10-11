package com.hilbing.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hilbing.news.domain.usecase.GetNewsHeadlinesUseCase
import com.hilbing.news.domain.usecase.GetSearchedNewsUseCase
import com.hilbing.news.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app : Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase
        ) as T
    }
}