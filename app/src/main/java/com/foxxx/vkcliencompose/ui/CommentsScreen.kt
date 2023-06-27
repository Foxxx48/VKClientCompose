package com.foxxx.vkcliencompose.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.PostComment
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CommentsScreen(
    feedPost: FeedPost
) {
    val comments = mutableListOf<PostComment>()
        repeat(10) {
            comments.add(PostComment(id=it))
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Comments for FeedPostId: ${feedPost.id}",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                       Icon(
                           imageVector = Icons.Filled.ArrowBack,
                           contentDescription = "",
                       tint = MaterialTheme.colorScheme.onSecondary)

                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 72.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(3) {
                VKCommentCard(postComment = PostComment(id = it))
            }
        }
    }
}

@Preview
@Composable
fun PreviewCommentScreenLightTheme() {
    VKClientComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CommentsScreen(FeedPost())
    }
}

@Preview
@Composable
fun PreviewCommentScreenDarkTheme() {
    VKClientComposeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        CommentsScreen(FeedPost())
    }
}

