package com.foxxx.vkcliencompose.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.foxxx.vkcliencompose.di.AppComponent
import com.foxxx.vkcliencompose.di.DaggerAppComponent

class NewsFeedApplication : Application() {

    val component: AppComponent by lazy {

       DaggerAppComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): AppComponent {
    Log.d("RECOMPOSITION_TAG", "getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}