package com.foxxx.vkcliencompose.data.model

import com.google.gson.annotations.SerializedName

data class CommentsResponseDto(
    @SerializedName("response") val commentsContentDto: CommentsContentDto
)