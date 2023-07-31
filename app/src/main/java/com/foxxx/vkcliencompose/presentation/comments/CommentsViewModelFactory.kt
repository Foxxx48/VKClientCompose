package com.foxxx.vkcliencompose.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foxxx.vkcliencompose.domain.entity.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost,
    private val application: Application
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass == CommentsViewModel::class.java) {
            return CommentsViewModel(feedPost, application) as T
        }

        if (modelClass == CommentsViewModelWithFlow::class.java) {
            return CommentsViewModelWithFlow(feedPost, application) as T
        }

        throw  RuntimeException("Wrong ViewModel")

    }
}