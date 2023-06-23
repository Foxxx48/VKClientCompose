package com.foxxx.vkcliencompose.ui

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.StatisticItem


class MainViewModel : ViewModel() {


    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(
                    id = it,
                    publicationDate = "Publication Date $it"
                )
            )
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initialList)
    val feedPosts: LiveData<List<FeedPost>> = _feedPosts

    fun updateCount(
        feedPost: FeedPost,
        item: StatisticItem
    ) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics =
            oldStatistics.toMutableStateList().apply {
                replaceAll { oldItem ->
                    if (oldItem.type == item.type) {
                        oldItem.copy(count = oldItem.count + 1)
                    } else {
                        oldItem
                    }
                }
            }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }

    fun deleteVKModel(model: FeedPost) {

        val modifiedList =
            _feedPosts.value?.toMutableList()
                ?: mutableListOf()

        modifiedList.remove(model)

        _feedPosts.value = modifiedList
    }
}