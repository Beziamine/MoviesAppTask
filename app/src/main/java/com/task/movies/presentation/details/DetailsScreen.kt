package com.task.movies.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.presentation.details.components.DetailContent
import com.task.movies.util.Resource

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun DetailsScreen(
    movieId:Int,
    navController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val movieDetails = produceState<Resource<MoviesDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getMoviesDetails(filmId = movieId)
    }.value

    if (movieDetails is Resource.Success){
        DetailContent(navController = navController,
            moviesDetails = movieDetails.data!!,
            mediaType = "movie")
    } else{
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = Color.Red,
                strokeWidth = 2.dp)
        }
    }
}