package com.foxxx.vkcliencompose.di

import android.content.Context
import com.foxxx.vkcliencompose.presentation.ViewModelFactory
import com.foxxx.vkcliencompose.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun getViewModelFactory() : ViewModelFactory

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}