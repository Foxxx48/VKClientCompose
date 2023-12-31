package com.foxxx.vkcliencompose.data.repository

import android.app.Application
import com.foxxx.vkcliencompose.data.mapper.CommentsMapper
import com.foxxx.vkcliencompose.data.mapper.NewsFeedMapper
import com.foxxx.vkcliencompose.data.network.ApiFactory
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.PostComment
import com.foxxx.vkcliencompose.domain.entity.StatisticItem
import com.foxxx.vkcliencompose.domain.entity.StatisticType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsFeedRepository(application: Application)  {
    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()
    private val commentsMapper = CommentsMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private val _comments = mutableListOf<PostComment>()
    val comments: List<PostComment>
        get() = _comments.toList()

    private var nextFrom: String? = null

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    suspend fun loadRecommendations(): List<FeedPost> {
        val startFrom = nextFrom

        if (startFrom == null && feedPosts.isNotEmpty()) return feedPosts

        val response =
            if (startFrom == null) {
                apiService.loadRecommendations(getAccessToken())
            } else {
                apiService.loadRecommendations(getAccessToken(), startFrom)
            }

        nextFrom = response.newsFeedContent.nextFrom

        val posts = mapper.mapResponseToPosts(response)

        _feedPosts.addAll(posts)

        return feedPosts
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }
        val newLikesCount = response.likes.count

        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)

        val postIndex = _feedPosts.indexOf(feedPost)

        _feedPosts[postIndex] = newPost
    }

    suspend fun deleteFeedPost(feedPost: FeedPost) {
        apiService.ignorePost(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )

        _feedPosts.remove(feedPost)
    }

    suspend fun loadComments(feedPost: FeedPost): List<PostComment> {

        val response = apiService.getComments(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )

        val result =
            commentsMapper.mapResponseToPostComment(response)

        _comments.addAll(result)

        return comments
    }
}


