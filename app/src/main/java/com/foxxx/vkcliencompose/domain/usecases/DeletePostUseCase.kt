package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import javax.inject.Inject

class DeletePostUseCase @Inject constructor (private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repositoryWithFlow.deletePost(feedPost)
    }

}