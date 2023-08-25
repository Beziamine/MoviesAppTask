package com.task.movies.presentation.mylist

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navController: NavHostController,
    viewModel: MyListViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 MyListTopAppBar(onDeleteAllClicked = {
                     viewModel.deleteAllContent()
                 }, navController = navController
                 )
        },
        content = {
                 DisplayMyList(
                     navController = navController,
                     viewModel = viewModel,
                     onSwipeToDelete = {
                         viewModel.deleteOneFromMyList(it.listId)
                     }
                 )
        },
    )
}























