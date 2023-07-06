package com.foxxx.vkcliencompose.presentation.comments


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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.ui.VKCommentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPost
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost)
    )
    val screenState =
        viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value
    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "${currentState.feedPost.contentText}: ${currentState.feedPost.id}",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
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

                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    VKCommentCard(postComment = comment)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCommentScreenLightTheme() {
//    VKClientComposeTheme(
//        darkTheme = false,
//        dynamicColor = false
//    ) {
//        val comments = mutableListOf<PostComment>().apply {
//            repeat(10) {
//                add(PostComment(id = it))
//            }
//        }
//        CommentsScreen(feedPost = FeedPost(),
//        onBackPressed = {})
//    }
//}



