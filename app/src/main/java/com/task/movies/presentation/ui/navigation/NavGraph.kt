package com.task.movies.presentation.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.task.movies.presentation.details.DetailsScreen
import com.task.movies.presentation.homescreen.HomeScreen
import com.task.movies.presentation.mylist.ListScreen
import com.task.movies.util.Constants


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Movies.route
    ) {
        composable(route = BottomNavItem.Movies.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomNavItem.MyList.route) {
            ListScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(Constants.MOVIE_DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Constants.MOVIE_DETAILS_ARGUMENT_KEY)
                ?.let { DetailsScreen(it,navController) }
        }
    }
}