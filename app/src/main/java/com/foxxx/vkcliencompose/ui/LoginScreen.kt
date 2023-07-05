package com.foxxx.vkcliencompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foxxx.vkcliencompose.R
import com.foxxx.vkcliencompose.ui.theme.VKClientComposeTheme


@Composable
fun LoginScreen(
    onLoginClickListener : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = androidx.compose.material3.MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_vk_logo
                ),
                contentDescription = "vk_logo",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { onLoginClickListener()},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.dark_blue_vk),
                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = stringResource(R.string.button_login)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLSDarkTheme() {
    VKClientComposeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        LoginScreen(
        onLoginClickListener = {}
        )
    }
}

@Preview
@Composable
fun PreviewLSLightTheme() {
    VKClientComposeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        LoginScreen(
            onLoginClickListener = {}
        )
    }
}