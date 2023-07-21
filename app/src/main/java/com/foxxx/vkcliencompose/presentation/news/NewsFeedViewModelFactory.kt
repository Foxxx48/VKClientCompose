package com.foxxx.vkcliencompose.presentation.news

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsFeedViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isInstance(NewsFeedViewModel::class.java)) {
            return NewsFeedViewModel(application) as T
        }
        if (modelClass.isInstance(NewsFeedViewModelWithFlow::class.java)) {
            return NewsFeedViewModelWithFlow(application) as T
        }
        throw  RuntimeException("Wrong ViewModel")
    }
}