package com.foxxx.vkcliencompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.foxxx.vkcliencompose.domain.FeedPost

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Test(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val models = viewModel.vkModels.observeAsState(listOf())

    LazyColumn(
        modifier = modifier,
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
                val feedPost = viewModel.feedPost.observeAsState(FeedPost())

                VKCard(
                    modifier = Modifier
                        .padding(8.dp),
                    feedPost = model,
                    onViewClickListener = { viewModel.updateCount(it) },
                    onShareClickListener = { viewModel.updateCount(it) },
                    onCommentClickListener = viewModel::updateCount,
                    onLikeClickListener = viewModel::updateCount
                )


            }
        }
    }

}