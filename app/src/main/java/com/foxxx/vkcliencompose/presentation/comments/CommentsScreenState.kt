package com.foxxx.vkcliencompose.presentation.comments

import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}