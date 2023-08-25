package com.task.movies.presentation.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = White,
    secondary = PrimaryDark,
    secondaryVariant = SecondaryDark,
    surface = White,
    background = White

)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Black,
    secondary = SecondaryLight,
    secondaryVariant = PrimaryLight,
    surface = Black,
    background = Black


)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}