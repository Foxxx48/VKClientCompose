package com.foxxx.vkcliencompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun Test(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val models = viewModel.feedPosts.observeAsState(listOf())

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 16.dp,
            bottom = 72.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            models.value,
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
                    onCommentClickListener = { statisticItem ->
                        viewModel.updateCount(
                            model,
                            statisticItem
                        )
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