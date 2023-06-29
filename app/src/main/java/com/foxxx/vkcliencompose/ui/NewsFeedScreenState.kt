package com.foxxx.vkcliencompose.ui

import com.foxxx.vkcliencompose.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>
    ) : NewsFeedScreenState()
}