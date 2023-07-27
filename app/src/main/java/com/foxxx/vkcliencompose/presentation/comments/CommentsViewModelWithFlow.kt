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

//    private val repositoryCommentsFlow = flow<CommentsScreenState> {
//        Log.d("TAG", "flow<CommentsScreenState> launched in CommentsViewModelWithFlow")
//            repository.loadComments(feedPost)
//                .map {
//                    CommentsScreenState.Comments(
//                        feedPost = feedPost,
//                        comments = it
//                    )
//                }
//
//    }
//
//    val screenState = repositoryCommentsFlow
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.Lazily,
//            initialValue = CommentsScreenState.Initial
//
//        )

    val screenState = repository.loadComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}