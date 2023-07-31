package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow

class CheckAuthStateUseCase(private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    suspend operator fun invoke() {
        repositoryWithFlow.checkAuthState()
    }

}