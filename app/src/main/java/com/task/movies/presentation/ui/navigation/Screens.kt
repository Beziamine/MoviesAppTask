package com.task.movies.presentation.ui.navigation

import com.task.movies.R


sealed class Screen(val route: String) {
    object Detail : Screen("detail/{movieId}") {
        fun passMovieId(movieId: Int) = "detail/$movieId"
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String){
    object Movies : BottomNavItem("Movies", R.drawable.movies,"Movies")
    object MyList : BottomNavItem("My list", R.drawable.my_list,"MyList")
}

