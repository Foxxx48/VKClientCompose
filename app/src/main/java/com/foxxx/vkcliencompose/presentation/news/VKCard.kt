package com.foxxx.vkcliencompose.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.foxxx.vkcliencompose.R
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.entity.StatisticItem
import com.foxxx.vkcliencompose.domain.entity.StatisticType
import com.foxxx.vkcliencompose.ui.theme.DarkRed

@Composable
fun VKCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
    isLiked: Boolean

) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PostHeader(feedPost)

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = feedPost.contentText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = feedPost.contentImageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentDescription = "poster",
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Statistics(
                statistics = feedPost.statistics,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener,
                isLiked = isLiked

            )
            //            BoxColors()
        }
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit,
    isLiked: Boolean
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Row(
            modifier = Modifier
                .weight(1f),
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            ViewsCountInfo(
                views = formatStatisticCount(viewsItem.count)
            )
        }

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            val likesItem = statistics.getItemByType(StatisticType.LIKES)

            SharesCountInfo(
                shares = formatStatisticCount(sharesItem.count),
            )
            CommentsCountInfo(comments = formatStatisticCount(commentsItem.count),
                onItemClickListener = {
                    Log.d("Test", "SMCard CommentsCountInfo Clicked")
                    onCommentClickListener(commentsItem)
                })
            LikesCountInfo(
                iconResId = if (isLiked) R.drawable.ic_like_set else R.drawable.ic_like,
                text = formatStatisticCount(likesItem.count),
                onItemClickListener = {
                    onLikeClickListener(likesItem)
                },
                tint = if (isLiked) DarkRed else MaterialTheme.colorScheme.onSecondary
            )

        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException("Wrong type")
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", (count / 1000))
    } else if (count > 1000) {
        String.format("%.1fK", (count / 1000f))
    } else {
        count.toString()
    }
}


@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = feedPost.communityName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_three_dots_vertical),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun ViewsCountInfo(
    views: String,
) {
    Row() {
        Text(
            text = views,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_views_count),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )

    }
}

@Composable
private fun SharesCountInfo(
    shares: String,
) {
    Row() {
        Text(
            text = shares,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.width(6.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "",

            )
    }
}

@Composable
private fun CommentsCountInfo(
    comments: String,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            Log.d("Test", "Comments modifier clicked")
            onItemClickListener()
        }
    ) {
        Text(
            text = comments,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.width(6.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = ""
        )
    }
}

@Composable
private fun LikesCountInfo(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.onSecondary
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Text(
            text = text,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.width(6.dp))
        Icon(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint
        )

    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.onSecondary
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun BoxColors() {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Primary")
        }
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            Text(text = "onPrimary")
        }
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(text = "Secondary")
        }
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.onSecondary)
        ) {
            Text(text = "onSecondary")
        }
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.tertiary)
        ) {
            Text(text = "tertiary")
        }
    }
}