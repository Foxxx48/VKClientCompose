package com.foxxx.vkcliencompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepository
import com.foxxx.vkcliencompose.domain.entity.FeedPost
import kotlinx.coroutines.launch


class CommentsViewModel(
    feedPost: FeedPost, application: Application
    ) : AndroidViewModel(application) {

    private val _screenState =
        MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState



    private val repository = NewsFeedRepository(application)

    init {
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments =  repository.loadComments(feedPost)
            _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
        }
    }
}