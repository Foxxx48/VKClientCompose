package com.foxxx.vkcliencompose.domain.usecases

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.PostComment
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoadCommentsUseCase @Inject constructor (private val repositoryWithFlow: NewsFeedRepositoryWithFlow) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> = repositoryWithFlow.loadComments(feedPost)

}