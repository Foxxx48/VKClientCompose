package com.foxxx.vkcliencompose.data.mapper

import android.icu.text.SimpleDateFormat
import com.foxxx.vkcliencompose.data.model.CommentsResponseDto
import com.foxxx.vkcliencompose.domain.PostComment
import java.util.Date
import java.util.Locale

class CommentsMapper {
    fun mapResponseToPostComment(responseDto: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()


        val comments = responseDto.commentsContentDto.comments
        val profiles = responseDto.commentsContentDto.profiles

        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val profile = profiles.find { it.id == comment.authorId } ?: break
//            val profile = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${profile.firstName} ${profile.lastName}",
                authorAvatarUrl = profile.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimeStampToDate(comment.date) ,
            )

            result.add(postComment)

        }
        return result
    }

    private fun mapTimeStampToDate(timeStamp: Long): String {
        val date = Date(timeStamp * 1000)
        return SimpleDateFormat("d MMMM yyyy, HH:mm", Locale.getDefault()).format(date)
    }
}