package com.foxxx.vkcliencompose.presentation.comments


import android.app.Application
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.foxxx.vkcliencompose.R
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.ui.VKCommentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPost
) {

    val viewModel: CommentsViewModelWithFlow = viewModel(
        factory = CommentsViewModelFactory(
            feedPost,
        application = LocalContext.current.applicationContext as Application)
    )
//    val screenState =
//        viewModel.screenState.observeAsState(CommentsScreenState.Initial)

    val screenState =
        viewModel.screenState.collectAsState(CommentsScreenState.Initial)

    val currentState = screenState.value
    Log.d("TAG", "loadComments() - ${screenState.value.javaClass.canonicalName}")
    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.comments_name),
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
    } else {
        Log.d("TAG", "loadComments() - currentState is NOT CommentsScreenState.Comments")
    }
}




