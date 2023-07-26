package com.foxxx.vkcliencompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlow
import com.foxxx.vkcliencompose.domain.FeedPost
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CommentsViewModelWithFlow(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryWithFlow(application)

    val screenState = flow<CommentsScreenState> {
        viewModelScope.launch{
            repository.loadComments(feedPost)
                .map {
                    CommentsScreenState.Comments(
                        feedPost = feedPost,
                        comments = it
                    )
                }
        }
    }






}