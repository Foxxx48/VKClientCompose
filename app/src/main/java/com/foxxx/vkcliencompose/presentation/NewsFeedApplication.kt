package com.foxxx.vkcliencompose.presentation

import android.app.Application
import com.foxxx.vkcliencompose.di.AppComponent
import com.foxxx.vkcliencompose.di.DaggerAppComponent

class NewsFeedApplication : Application() {

    val component: AppComponent by lazy {

       DaggerAppComponent.factory().create(this)
    }
}