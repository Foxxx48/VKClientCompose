package com.foxxx.vkcliencompose.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.domain.usecases.CheckAuthStateUseCase
import com.foxxx.vkcliencompose.domain.usecases.LoadAuthStateFlowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadAuthStateFlowUseCase: LoadAuthStateFlowUseCase,
    private val checkAuthStateUseCase: CheckAuthStateUseCase
) : ViewModel() {


    val authStateFlow = loadAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }

    }
}