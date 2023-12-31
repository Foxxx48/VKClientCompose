package com.foxxx.vkcliencompose.data.mapper

import android.icu.text.SimpleDateFormat
import com.foxxx.vkcliencompose.data.model.NewsFeedResponseDto
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.StatisticItem
import com.foxxx.vkcliencompose.domain.entity.StatisticType
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.absoluteValue

class NewsFeedMapper @Inject constructor() {
    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                communityId = post.communityId,
                publicationDate = mapTimeStampToDate(post.date * 1000),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)

        }
        return result
    }

    private fun mapTimeStampToDate(timeStamp: Long): String {
        val date = Date(timeStamp)
        return SimpleDateFormat("d MMMM yyyy, HH:mm", Locale.getDefault()).format(date)
    }
}