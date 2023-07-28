package com.foxxx.vkcliencompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlow
import com.foxxx.vkcliencompose.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModelWithFlow(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryWithFlow(application)

    val screenState = repository.loadComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}