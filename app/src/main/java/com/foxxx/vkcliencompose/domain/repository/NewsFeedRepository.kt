package com.foxxx.vkcliencompose.domain.repository

import com.foxxx.vkcliencompose.domain.entity.AuthState
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun loadAuthStateFlow(): StateFlow<AuthState>

    fun loadRecommendations(): List<FeedPost>

    fun loadComments(feedPost: FeedPost): List<PostComment>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun deletePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}