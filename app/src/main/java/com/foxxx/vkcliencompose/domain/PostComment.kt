package com.foxxx.vkcliencompose.domain

import com.foxxx.vkcliencompose.R


data class PostComment(
    val id: Int = 0,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.comment_account_icon,
    val commentText: String = "Comment text",
    val publicationDate: String = "14:00"
)