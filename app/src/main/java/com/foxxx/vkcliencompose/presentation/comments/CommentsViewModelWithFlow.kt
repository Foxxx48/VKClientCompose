package com.foxxx.vkcliencompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlow
import com.foxxx.vkcliencompose.domain.FeedPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommentsViewModelWithFlow(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryWithFlow(application)

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val repositoryCommentsFlow = flow<CommentsScreenState> {
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

    val screenState = repositoryCommentsFlow
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = CommentsScreenState.Initial

        )
}