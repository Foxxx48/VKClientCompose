package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.AuthState
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoadAuthStateFlowUseCase @Inject constructor (private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    operator fun invoke(): StateFlow<AuthState> = repositoryWithFlow.loadAuthStateFlow()

}