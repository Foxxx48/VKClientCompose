package com.foxxx.vkcliencompose.data.model

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("id") val id: Long,
    @SerializedName("from_id") val authorId: Long,
    @SerializedName("date") val date: Long,
    @SerializedName("text") val text: String,
    @SerializedName("post_id") val postId: Long,
    @SerializedName("owner_id") val ownerId: Long,

)
