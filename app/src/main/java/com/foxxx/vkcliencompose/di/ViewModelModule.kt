package com.foxxx.vkcliencompose.di

import androidx.lifecycle.ViewModel
import com.foxxx.vkcliencompose.presentation.main.MainViewModel
import com.foxxx.vkcliencompose.presentation.news.NewsFeedViewModelWithFlow
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(NewsFeedViewModelWithFlow::class)
    @Binds
    fun bindNewsFeedViewModelWithFlow(viewModel: NewsFeedViewModelWithFlow): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}