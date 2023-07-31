package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import kotlinx.coroutines.flow.StateFlow

class LoadRecommendationsUseCase(private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    operator fun invoke(): StateFlow<List<FeedPost>> = repositoryWithFlow.loadRecommendations()

}