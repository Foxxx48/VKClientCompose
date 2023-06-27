package com.foxxx.vkcliencompose.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.PostComment
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    comments: List<PostComment>
) {
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
                            tint = MaterialTheme.colorScheme.onSecondary
                        )

                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 72.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            items(items= comments) {
//                VKCommentCard(postComment = PostComment(id = it))
//            }
            items(
                items = comments,
                key = { it.id }
            ) {comment ->
                VKCommentCard(postComment = comment)
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
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        CommentsScreen(FeedPost(),
        comments)
    }
}

@Preview
@Composable
fun PreviewCommentScreenDarkTheme() {
    VKClientComposeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        CommentsScreen(FeedPost(), comments)
    }
}

