package com.foxxx.vkcliencompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    val authLauncher = rememberLauncherForActivityResult(
                        contract = VK.getVKAuthActivityResultContract()
                        ) {
                        when (it) {
                            is VKAuthenticationResult.Success -> {
                                // User passed authorization
                                Log.d("TEST_VK", "Success Auth")
                            }
                            is VKAuthenticationResult.Failed -> {
                                // User didn't pass authorization
                                Log.d("TEST_VK", "Failed Auth")
                            }

                        }
                    }

                    authLauncher.launch(arrayListOf(VKScope.WALL))

                }
            }
        }
    }
}

