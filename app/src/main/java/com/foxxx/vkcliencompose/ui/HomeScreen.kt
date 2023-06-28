package com.foxxx.vkcliencompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foxxx.vkcliencompose.domain.FeedPost
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
) {
    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = screenState.value) {
        is HomeScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                posts = currentState.posts
            )
        }
        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.feedPost,
                comments = currentState.comments,
                onBackPressed = {
                    viewModel.closeComments()
                }
            )
        }
        HomeScreenState.Initial -> {

        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    viewModel: MainViewModel,
    posts: List<FeedPost>
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
        items(
            items = posts,
            key = {
                it.id
            }) { model ->

            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.deleteVKModel(model)
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .animateItemPlacement(),
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Red.copy(0.5f))
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Delete item",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                }
            ) {
                VKCard(
                    feedPost = model,
                    onViewClickListener = { statisticItem ->
                        viewModel.updateCount(
                            model,
                            statisticItem
                        )
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(
                            model,
                            statisticItem
                        )
                    },
                    onCommentClickListener = {
                        viewModel.showComments(model)

                    },
                    onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(
                            model,
                            statisticItem
                        )
                    }
                )
            }
        }
    }
}