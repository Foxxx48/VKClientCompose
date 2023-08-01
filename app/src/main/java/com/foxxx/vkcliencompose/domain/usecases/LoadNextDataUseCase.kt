package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor (private val newsFeedRepository: NewsFeedRepositoryWithFlow) {
    suspend operator fun invoke() {
        newsFeedRepository.loadNextData()
    }
}