package com.foxxx.vkcliencompose.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)

    Scaffold(
        bottomBar = {
            BottomNavigation() {

                val items =
                    listOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Account
                    )

                items.forEach() {  item ->
                    BottomNavigationItem(
                        selected = selectedNavItem == item,
                        onClick = { viewModel.selectNavItem(item) },
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
        when(selectedNavItem) {
            NavigationItem.Account -> TextCount(name = "Account")
            NavigationItem.Favorite -> TextCount(name = "Favorite")
            NavigationItem.Home -> HomeScreen(viewModel = viewModel)
        }
    }
}

@Composable
private fun TextCount(name: String) {
    var count by remember {
        mutableStateOf(0)
    }
    
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count $count",
        color = Color.Black
    )

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

