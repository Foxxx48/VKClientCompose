package com.foxxx.vkcliencompose.ui

import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.PostComment

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    data class Posts(
        val posts: List<FeedPost>
    ) : HomeScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : HomeScreenState()

}