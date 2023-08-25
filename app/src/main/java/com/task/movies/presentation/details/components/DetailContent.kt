package com.task.movies.presentation.details.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.task.movies.R
import com.task.movies.domain.models.Genres
import com.task.movies.domain.models.MoviesDetails
import com.task.movies.domain.models.MyList
import com.task.movies.domain.models.SpokenLanguages
import com.task.movies.presentation.mylist.MyListViewModel
import com.task.movies.presentation.ui.theme.EXTRA_SMALL_PADDING
import com.task.movies.presentation.ui.theme.INFO_ICON_SIZE
import com.task.movies.presentation.ui.theme.MEDIUM_PADDING
import com.task.movies.presentation.ui.theme.SMALL_PADDING
import com.task.movies.util.Constants

@Composable
fun DetailContent(navController: NavHostController,
                  moviesDetails: MoviesDetails,
                  mediaType:String,
                  viewModel: MyListViewModel = hiltViewModel()
){

    val addToMyList = viewModel.addToMyList.value
    val context = LocalContext.current

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(MEDIUM_PADDING)) {

        DetailHeader(onCloseClick = { navController.popBackStack()},
            onClick = { if (addToMyList != 0) {
                viewModel.deleteOneFromMyList(listId = moviesDetails.id!!)
                Toast.makeText(context, context.getString(R.string.removed_from_my_list), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.added_to_watchlist), Toast.LENGTH_SHORT
                ).show()
                viewModel.addToMyList(
                    MyList(
                        listId = moviesDetails.id!!,
                        imagePath = "${Constants.IMAGE_BASE_URL}/${moviesDetails.poster_path}",
                        title = moviesDetails.title.toString(),
                        description = moviesDetails.overview.toString(),
                        mediaType = mediaType,
                        releaseDate = moviesDetails.release_date!!.substring(0,4)
                    )
                )
            }},
            tint = if (addToMyList != 0) {
                Color.Yellow
            } else {
                MaterialTheme.colors.surface
            })

        Column {
            Image(
                modifier = Modifier
                    .width(160.dp)
                    .height(220.dp)
                    .align(Alignment.CenterHorizontally),
                painter = rememberImagePainter(
                    data = "${Constants.IMAGE_BASE_URL}/${moviesDetails.poster_path}",
                    builder = {
                        placeholder(R.drawable.ic_placeholder)
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.background_image)
            )
            Spacer(modifier = Modifier.height(MEDIUM_PADDING))
            Text(
                text = moviesDetails.title.toString(),
                color = MaterialTheme.colors.surface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.release),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.release_date.toString().substring(0,7),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.all_genres),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.genres!!.concatGenres(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.overview),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.overview.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.homepage),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.homepage.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.budget),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.budget.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.revenue),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.revenue.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.spoken_languages),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.spokenLanguages!!.concatLanguages(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.status),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.status.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
            Row(Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(end = SMALL_PADDING),
                    text = stringResource(R.string.runtime),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = moviesDetails.runtime.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))
        }
    }
}

@Composable
fun DetailHeader(
    onCloseClick: () -> Unit,
    onClick:()->Unit,
    tint: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { onCloseClick() })
        { Icon(
            modifier = Modifier.size(INFO_ICON_SIZE),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.close_button),
            tint = MaterialTheme.colors.surface
        )
        }
        IconButton(onClick = {
            onClick()
        }) {
            Icon(
                modifier = Modifier.size(INFO_ICON_SIZE),
                painter = painterResource(id = R.drawable.star),
                contentDescription = stringResource(R.string.like_movie_show),
                tint = tint
            )
        }
    }
}

fun List<Genres>.concatGenres() = this.joinToString(" ") { it.name }
fun List<SpokenLanguages>.concatLanguages() = this.joinToString(" ") { it.name }