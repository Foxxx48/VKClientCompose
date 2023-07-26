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
import com.foxxx.vkcliencompose.ui.LoginScreen
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKClientComposeTheme(
                dynamicColor = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    ) {
                    val viewModel: MainViewModel = viewModel()
                    val authState = viewModel.authState.collectAsState(AuthState.Initial)
                    val authLauncher = rememberLauncherForActivityResult(
                        contract = VK.getVKAuthActivityResultContract()
                    ) {
                        viewModel.performAuthResult(it)
                    }

                    when (authState.value) {
                        is AuthState.Authorized -> MainScreen()

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

