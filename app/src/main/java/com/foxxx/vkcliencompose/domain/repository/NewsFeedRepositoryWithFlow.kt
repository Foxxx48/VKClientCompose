package com.foxxx.vkcliencompose.domain.repository

import com.foxxx.vkcliencompose.domain.entity.AuthState
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepositoryWithFlow {

    fun loadAuthStateFlow(): StateFlow<AuthState>

    fun loadRecommendations(): StateFlow<List<FeedPost>>

    fun loadComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun deletePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}