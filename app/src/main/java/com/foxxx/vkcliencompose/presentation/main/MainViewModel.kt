package com.foxxx.vkcliencompose.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlowImpl
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryWithFlow = NewsFeedRepositoryWithFlowImpl(application)


    val authStateFlow = repositoryWithFlow.authStateFlow

    fun performAuthResult() {
        viewModelScope.launch {
            repositoryWithFlow.checkAuthState()
        }

    }
}