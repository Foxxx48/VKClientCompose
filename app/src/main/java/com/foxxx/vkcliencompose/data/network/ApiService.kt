package com.foxxx.vkcliencompose.data.network

import com.foxxx.vkcliencompose.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun loadRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    @GET("likes.add?v=5.131")
    suspend fun addLike(
        @Query("access_token") token: String,
        @Query("type") post: String = "post",
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long
    )
}