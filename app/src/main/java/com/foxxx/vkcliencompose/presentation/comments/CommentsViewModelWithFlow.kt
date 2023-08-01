package com.foxxx.vkcliencompose.presentation.comments

import androidx.lifecycle.ViewModel
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.domain.usecases.LoadCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModelWithFlow @Inject constructor(
    private val feedPost: FeedPost,
    private val loadCommentsUseCase: LoadCommentsUseCase
) : ViewModel() {


    val screenState = loadCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}