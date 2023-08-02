package com.foxxx.vkcliencompose.di

import androidx.lifecycle.ViewModel
import com.foxxx.vkcliencompose.presentation.comments.CommentsViewModelWithFlow
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {
    @IntoMap
    @ViewModelKey(CommentsViewModelWithFlow::class)
    @Binds
    fun bindCommentsViewModelWithFlow(viewModel: CommentsViewModelWithFlow): ViewModel
}