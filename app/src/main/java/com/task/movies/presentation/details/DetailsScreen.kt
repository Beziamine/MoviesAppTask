package com.task.movies.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import coil.annotation.ExperimentalCoilApi
import com.task.movies.R
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.presentation.details.components.DetailContent
import com.task.movies.util.Resource
import retrofit2.HttpException
import java.io.IOException

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

    when (movieDetails) {
        is Resource.Success -> {
            DetailContent(navController = navController,
                moviesDetails = movieDetails.data!!,
                mediaType = "movie")
        }
        is Resource.Loading -> {
            Box(Modifier.fillMaxSize()
                .background(color = MaterialTheme.colors.secondary)){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                    color = Color.Red,
                    strokeWidth = 2.dp)
            }
        }
        else -> {
            Box(Modifier.fillMaxSize().background(color = MaterialTheme.colors.secondary)){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.couldn_t_reach_server_check_your_internet_connection),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    }
}