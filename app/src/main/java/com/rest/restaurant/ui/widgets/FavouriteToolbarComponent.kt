package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rest.restaurant.ui.theme.AccentBlue

@Composable
fun FavouriteToolbarComponent(
    count: Int,
    isFilled: Boolean,
) {
    val text = if (count >= 100) { "∞" } else { count.toString() }
    val textColor = if (isFilled) { Color.White } else { AccentBlue }
    val imageVector = if (isFilled) { Icons.Filled.Favorite } else { Icons.Filled.FavoriteBorder }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        IconLikeComponent(
            icon = imageVector
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun FavouriteToolbarComponentPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        FavouriteToolbarComponent(
            count = 17,
            isFilled = true,
        )
        FavouriteToolbarComponent(
            count = 17,
            isFilled = false,
        )
        FavouriteToolbarComponent(
            count = 100,
            isFilled = true,
        )
        FavouriteToolbarComponent(
            count = 100,
            isFilled = false,
        )
    }
}