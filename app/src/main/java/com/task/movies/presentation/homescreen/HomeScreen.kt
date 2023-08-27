package com.task.movies.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.task.movies.R
import com.task.movies.presentation.homescreen.components.Genres
import com.task.movies.util.TrailingIconState
import com.task.movies.presentation.ui.navigation.Screen
import com.task.movies.presentation.ui.theme.LARGE_PADDING
import com.task.movies.presentation.ui.theme.MEDIUM_PADDING
import com.task.movies.presentation.ui.theme.SMALL_PADDING
import com.task.movies.util.Constants.IMAGE_BASE_URL
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoilApi
@Composable
@ExperimentalMaterialApi
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val discoverMovies = viewModel.discoverMovies.value.collectAsLazyPagingItems()
    val searchTerm = viewModel.searchQuery.value

    Column (modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.secondary)
        .padding(MEDIUM_PADDING, MEDIUM_PADDING, MEDIUM_PADDING, LARGE_PADDING)) {
        SearchBar(
            search = searchTerm,
            onSearchClicked = {
                viewModel.search(query = it)
            },
            onTextChanged = {
                viewModel.updateSearchQuery(query = it)
            }
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Text(
                    text = stringResource(R.string.categories),
                    color = MaterialTheme.colors.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
            }
            item {
                Genres(
                    viewModel = viewModel()
                )
                Spacer(modifier = Modifier.height(MEDIUM_PADDING))
            }
            item {
                Text(
                    text = stringResource(R.string.discover_movies),
                    color = MaterialTheme.colors.surface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(MEDIUM_PADDING))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(discoverMovies){ film->
                MovieItem(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(route = Screen.Detail.passMovieId(film?.id!!))
                        },
                    imageUrl = "$IMAGE_BASE_URL/${film!!.poster_path}",
                    title = film.title,
                    releaseYear = film.release_date
                )
            }
            if (discoverMovies.loadState.append == LoadState.Loading){
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        color = Color.Red,
                        strokeWidth = 2.dp
                    )
                }
            }
        }

        discoverMovies.apply {
            loadState
            when(loadState.refresh){
                is LoadState.Loading ->{
                    Box(Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                            color = Color.Red,
                            strokeWidth = 2.dp)
                    }
                }
                is LoadState.Error ->{
                    val error = discoverMovies.loadState.refresh as LoadState.Error
                    Box(Modifier.fillMaxSize()){
                        Text(modifier = Modifier.align(Alignment.Center),
                            text = when(error.error){
                                is HttpException ->{
                                    stringResource(R.string.oops_something_went_wrong)
                                }
                                is IOException ->{
                                    stringResource(R.string.couldn_t_reach_server_check_your_internet_connection)
                                }
                                else->{
                                    stringResource(R.string.unknown_error)
                                }
                            },
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.surface
                        )
                    }
                }
                else -> {}
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun MovieItem(
    modifier: Modifier,
    title : String,
    releaseYear : String,
    imageUrl:String,
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING)
    ){
        Row (modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondaryVariant)){
            Image(
                modifier= Modifier.weight(3f),
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        placeholder(R.drawable.ic_placeholder)
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.image_banner)
            )
            Column(
                modifier = modifier
                    .weight(7f)
                    .padding(SMALL_PADDING)
            ){
                Text(
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = title
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White,
                    text = releaseYear.substring(0,4),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    search:String,
    onSearchClicked:(String) -> Unit,
    onTextChanged:(String) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    var trailingIconState = viewModel.trailingIconState.value
    Surface(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation
    ){
        TextField(
            modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.secondaryVariant),
            value = search,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colors.surface
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.surface,
                cursorColor = MaterialTheme.colors.surface,
                focusedBorderColor = MaterialTheme.colors.secondaryVariant,
                unfocusedBorderColor = MaterialTheme.colors.secondaryVariant),
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.subtitle1,
            leadingIcon = {
                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = MaterialTheme.colors.surface
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when(trailingIconState){
                            TrailingIconState.READY_TO_DELETE ->{
                                onTextChanged("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE ->{
                                if(search.isNotEmpty()){
                                    onTextChanged("")
                                }else {
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_search_bar),
                        tint = MaterialTheme.colors.surface
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                onSearchClicked(search)
            }
        )
    }
}
