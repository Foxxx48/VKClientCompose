package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoadRecommendationsUseCase @Inject constructor (private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    operator fun invoke(): StateFlow<List<FeedPost>> = repositoryWithFlow.loadRecommendations()

}