package com.foxxx.vkcliencompose.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideEffectTest(number: MyNumber) {
    Column {
        LazyColumn {
            item {
                repeat(10) {
                    Text(text = "My number is: ${number.a}")
                }
            }
        }
        Spacer(
            modifier = Modifier
            .height(24.dp))

        number.a = 5

        LazyColumn {
            item {
                repeat(10) {
                    Text(text = "My number is: ${number.a}")
                }
            }
        }
    }
}

data class MyNumber(var a:Int)