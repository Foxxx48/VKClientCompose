package com.foxxx.vkcliencompose.di

import com.foxxx.vkcliencompose.domain.entity.FeedPost
import com.foxxx.vkcliencompose.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CommentsViewModelModule::class
    ]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance feedPost: FeedPost
        ): CommentsScreenComponent
    }
}