package com.foxxx.vkcliencompose.ui


import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
//    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }

                val items =
                    listOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Account
                    )

                items.forEachIndexed() { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = {
                            Icon(
                                item.icon, contentDescription = null,
//                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId),
                            )
                        },
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Green,
                    )
                }
            }
        },
    ) {
        Test(viewModel = viewModel)
    }
}

//@Preview
//@Composable
//fun PreviewLightTheme() {
//    VKClientComposeTheme(
//        darkTheme = false,
//        dynamicColor = false
//    ) {
//        MainScreen(MainViewModel(),
//            content = {}
//        )
//    }
//}
//
//@Preview
//@Composable
//fun PreviewDarkTheme() {
//    VKClientComposeTheme(
//        darkTheme = true,
//        dynamicColor = false
//    ) {
//        MainScreen(MainViewModel(),
//            content = {}
//        )
//    }
//}

