package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor (private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    suspend operator fun invoke() {
        repositoryWithFlow.checkAuthState()
    }

}