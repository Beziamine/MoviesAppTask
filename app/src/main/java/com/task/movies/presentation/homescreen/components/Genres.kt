package com.task.movies.presentation.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.task.movies.presentation.homescreen.HomeViewModel
import com.task.movies.presentation.ui.theme.SMALL_PADDING

@Composable
fun Genres(
    viewModel: HomeViewModel
) { 
    val genres = viewModel.movieGenres.value

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items( items = genres){genre ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
        ){
            Text(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.secondaryVariant)
                    .clickable {
                        viewModel.setGenre(genre.name)
                        viewModel.discoverMovies(genre.id)
                    }
                    .padding(SMALL_PADDING),
                text = genre.name,
                color = Color.White,
                style = MaterialTheme.typography.body1.merge()
            )
        }
        }
    }
}