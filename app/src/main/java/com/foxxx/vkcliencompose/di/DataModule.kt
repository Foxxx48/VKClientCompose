package com.foxxx.vkcliencompose.di

import android.content.Context
import com.foxxx.vkcliencompose.data.network.ApiFactory
import com.foxxx.vkcliencompose.data.network.ApiService
import com.foxxx.vkcliencompose.data.repository.NewsFeedRepositoryWithFlowImpl
import com.foxxx.vkcliencompose.domain.repository.NewsFeedRepositoryWithFlow
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @AppScope
    @Binds
    fun bindRepositoryWithFlow(impl: NewsFeedRepositoryWithFlowImpl): NewsFeedRepositoryWithFlow

    companion object {

        @AppScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @AppScope
        @Provides
        fun provideVKStorage(
            context: Context
        ): VKPreferencesKeyValueStorage = VKPreferencesKeyValueStorage(context)

    }
}