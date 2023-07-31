package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow

class ChangeLikeStatusUseCase(private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repositoryWithFlow.changeLikeStatus(feedPost)
    }

}