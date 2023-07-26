package com.foxxx.vkcliencompose.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlow
import com.foxxx.vkcliencompose.extentions.mergeWith
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryWithFlow = NewsFeedRepositoryWithFlow(application)

    private val authStateFromRepository = repositoryWithFlow.stateValuesFlow
    private val authStateFlow = MutableSharedFlow<AuthState>()
    val authState = authStateFromRepository
        .mergeWith(authStateFlow)


    fun performAuthResult(result: VKAuthenticationResult) {
        viewModelScope.launch {
            if (result is VKAuthenticationResult.Success) {
                authStateFlow.emit(AuthState.Authorized)
            }
            if (result is VKAuthenticationResult.Failed) {
                authStateFlow.emit(AuthState.NotAuthorized)
            }
        }

    }
}