package com.foxxx.vkcliencompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.foxxx.vkcliencompose.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentScreenContent: @Composable (FeedPost) -> Unit,

    ) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }

        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_FEED_POST_CONTENT_TEXT) {
                    type = NavType.StringType
                }
            )
        ) {
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 200500
            val feedPostContentText =
                it.arguments?.getString(Screen.KEY_FEED_POST_CONTENT_TEXT) ?: "Empty"
            commentScreenContent(
                FeedPost(
                    id = feedPostId,
                    contentText = feedPostContentText
                )
            )
        }
    }
}