package com.foxxx.vkcliencompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foxxx.vkcliencompose.domain.PostComment
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme


@Composable
fun VKCommentCard(
    modifier: Modifier = Modifier,
    postComment: PostComment
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp),
                painter = painterResource(id = postComment.authorAvatarId),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
            ) {

                Text(
                    text = "${postComment.authorName} CommentId: ${postComment.id}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = postComment.commentText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = postComment.publicationDate,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDarkTheme() {
    VKClientComposeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        VKCommentCard(postComment = PostComment())
    }
}

@Preview
@Composable
fun PreviewLightTheme() {
    VKClientComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        VKCommentCard(postComment = PostComment())
    }
}