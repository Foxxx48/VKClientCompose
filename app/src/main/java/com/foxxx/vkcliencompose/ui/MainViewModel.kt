package com.foxxx.vkcliencompose.ui

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foxxx.vkcliencompose.domain.FeedPost
import com.foxxx.vkcliencompose.domain.StatisticItem


class MainViewModel: ViewModel() {

    private val _feedPost = MutableLiveData(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(500) {
            add(
                FeedPost(
                    publicationDate = "Publication Date $it"
                )
            )
        }
    }

    private val _vkModels = MutableLiveData<List<FeedPost>>(initialList)
    val vkModels: LiveData<List<FeedPost>> = _vkModels

    fun updateCount(item: StatisticItem) {

        val oldStatistics = feedPost.value?.statistics ?: throw IllegalStateException()
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
        _feedPost.value = feedPost.value?.copy(statistics = newStatistics)
    }

    fun deleteVKModel(model: FeedPost) {

        val modifiedList =
            _vkModels.value?.toMutableList()
                ?: mutableListOf()

        modifiedList.remove(model)

        _vkModels.value = modifiedList
    }
}