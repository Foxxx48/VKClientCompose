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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.foxxx.vkcliencompose.navigation.AppNavGraph
import com.foxxx.vkcliencompose.navigation.Screen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items =
                    listOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )

                items.forEach() { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navHostController.navigate(item.screen.route) {
                            popUpTo(Screen.NewsFeed.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        } },
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
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = { HomeScreen(viewModel = viewModel) },
            favouriteScreenContent = { TextCount(name = "Favorite") },
            profileScreenContent = { TextCount(name = "Account") })
    }
}

@Composable
private fun TextCount(name: String) {
    var count by rememberSaveable() {
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

