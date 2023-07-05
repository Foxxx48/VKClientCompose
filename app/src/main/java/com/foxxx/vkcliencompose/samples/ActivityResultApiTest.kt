package com.foxxx.vkcliencompose.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview
@Composable
fun ActivityResultApiTest() {
    var imageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = {
                imageUri = it
            })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .size(200.dp)
                .align(CenterHorizontally),


            )

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.Black
            ),
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text(
                text = "Get Image",
                fontSize = 24.sp
            )

        }

    }
}