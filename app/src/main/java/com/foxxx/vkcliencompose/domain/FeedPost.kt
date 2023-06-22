package com.foxxx.vkcliencompose.domain

import com.foxxx.vkcliencompose.R

data class FeedPost(
    val communityName: String = "Foxxx",
    val publicationDate: String = "23:23",
    val avatarResId: Int = R.drawable.ic_fox,
    val contentText: String = "Horizon Zero Dawn",
    val contentImageResId: Int = R.drawable.pic_horizon_zero_down,
    val statistics : List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 233),
        StatisticItem(type = StatisticType.SHARES, 130),
        StatisticItem(type = StatisticType.COMMENTS, 30),
        StatisticItem(type = StatisticType.LIKES, 200)
    )
)
