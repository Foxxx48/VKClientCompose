package com.foxxx.vkcliencompose.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.foxxx.vkcliencompose.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 100500,
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
): Parcelable  {

    companion object {

        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {

            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
