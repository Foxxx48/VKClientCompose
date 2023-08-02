package com.foxxx.vkcliencompose.presentation.main


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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.foxxx.vkcliencompose.navigation.AppNavGraph
import com.foxxx.vkcliencompose.navigation.rememberNavigationState
import com.foxxx.vkcliencompose.presentation.ViewModelFactory
import com.foxxx.vkcliencompose.presentation.comments.CommentsScreen
import com.foxxx.vkcliencompose.presentation.news.NewsFeedScreen
import com.foxxx.vkcliencompose.ui.theme.DarkBlue


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory
) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = DarkBlue
            ) {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()


                val items =
                    listOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )

                items.forEach() { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected)
                                navigationState.navigateTo(item.screen.route)
                        },
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
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black,
                    )
                }
            }
        },
    ) {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                NewsFeedScreen(
                    viewModelFactory = viewModelFactory,
                    onCommentsClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )

            },
            commentScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },
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
//        MainScreen()
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
//        MainScreen()
//    }
//}

