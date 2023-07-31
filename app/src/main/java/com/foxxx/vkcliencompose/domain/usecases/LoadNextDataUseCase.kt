package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow

class LoadNextDataUseCase(private val newsFeedRepository: NewsFeedRepositoryWithFlow) {
    suspend operator fun invoke() {
        newsFeedRepository.loadNextData()
    }
}