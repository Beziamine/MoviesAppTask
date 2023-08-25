package com.task.movies.presentation.mylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.task.movies.R
import com.task.movies.presentation.ui.theme.INFO_ICON_SIZE
import com.task.movies.presentation.ui.theme.MEDIUM_PADDING


@Composable
fun MyListTopAppBar(
    onDeleteAllClicked:()->Unit,
    navController: NavHostController
) {
    Row(modifier = Modifier
        .background(color = MaterialTheme.colors.secondary)
        .fillMaxWidth()
        .padding(horizontal = MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier.weight(9f),
            text = stringResource(R.string.my_list),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.surface,
        )
        IconButton(
            modifier= Modifier.weight(1f),
            onClick = onDeleteAllClicked
        ) {
            Icon(
                tint = MaterialTheme.colors.surface,
                modifier = Modifier.size(INFO_ICON_SIZE),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_icon),
            )
        }
    }
}

