package com.foxxx.vkcliencompose.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.foxxx.vkcliencompose.domain.entity.AuthState
import com.foxxx.vkcliencompose.presentation.NewsFeedApplication
import com.foxxx.vkcliencompose.presentation.ViewModelFactory
import com.foxxx.vkcliencompose.ui.LoginScreen
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NewsFeedApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)


        setContent {
            VKClientComposeTheme(
                dynamicColor = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val viewModel: MainViewModel = viewModel(
                        factory = viewModelFactory
                    )
                    val authState = viewModel.authStateFlow.collectAsState(AuthState.Initial)
                    val authLauncher = rememberLauncherForActivityResult(
                        contract = VK.getVKAuthActivityResultContract()
                    ) {
                        viewModel.performAuthResult()
                    }

                    when (authState.value) {
                        is AuthState.Authorized -> MainScreen(viewModelFactory)

                        is AuthState.NotAuthorized ->
                            LoginScreen {
                                authLauncher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                            }

                        else -> {

                        }

                    }

                }
            }
        }
    }
}

