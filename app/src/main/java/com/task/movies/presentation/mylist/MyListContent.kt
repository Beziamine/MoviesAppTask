package com.task.movies.presentation.mylist

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.task.movies.R
import com.task.movies.domain.models.MyList
import com.task.movies.presentation.ui.navigation.Screen
import com.task.movies.presentation.ui.theme.EXTRA_SMALL_PADDING
import com.task.movies.presentation.ui.theme.MEDIUM_PADDING
import com.task.movies.presentation.ui.theme.SMALL_PADDING
import com.task.movies.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayMyList(
    navController: NavHostController,
    viewModel: MyListViewModel,
    onSwipeToDelete: (MyList)->Unit,
    ) {
    val list = viewModel.list.value.collectAsState(initial = emptyList())
    val currentList: State<List<MyList>> by remember { mutableStateOf(list) }

    Column (modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.secondary)
        .padding(MEDIUM_PADDING)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.secondary),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            items(
                items = currentList.value,
                key = {myList->
                    myList.listId
                }
            ){fav->
                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                if(isDismissed && dismissDirection == DismissDirection.EndToStart){
                    val scope = rememberCoroutineScope()
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(fav)
                    }
                }

                val degrees by animateFloatAsState(
                    targetValue =
                    if (dismissState.targetValue == DismissValue.Default)
                        0f
                    else
                        -45f
                )
                var itemAppeared by remember { mutableStateOf(false) }

                LaunchedEffect(key1 = true ){
                    itemAppeared = true
                }
                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300,
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ){
                    SwipeToDismiss(
                        state =dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                        background = {DeleteBackground(degrees = degrees)},
                        dismissContent = {
                            MyListItem(
                                modifier = Modifier.height(130.dp),
                                onClick = {
                                    navController.navigate(route = Screen.Detail.passMovieId(fav.listId))
                                }, favouriteMovie = fav)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MyListItem(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
    favouriteMovie:MyList?
) {
    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(SMALL_PADDING),
        elevation = EXTRA_SMALL_PADDING
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondaryVariant)
        ){
            Image(
                modifier= Modifier.weight(3f),
                painter = rememberImagePainter(
                    data = "${Constants.IMAGE_BASE_URL}/${favouriteMovie?.imagePath}",
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
                    text =(favouriteMovie?.title?: stringResource(R.string.no_title_provided))

                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White,
                    text = favouriteMovie?.releaseDate?: stringResource(R.string.no_release_date_provided),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Composable
fun DeleteBackground(degrees:Float) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(SMALL_PADDING),
        contentAlignment = Alignment.CenterEnd
    )
    {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = MaterialTheme.colors.surface
        )
    }
}

@Composable
fun EmptyListContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.alpha(ContentAlpha.disabled),
            text = stringResource(R.string.nothing_to_display),
            color = Color.White,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun EmptyContentPrev() {
    EmptyListContent()
}