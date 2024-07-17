package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onClick: () -> Unit,
) {
    val text = if (count >= 100) { "∞" } else { count.toString() }
    val textColor = if (isFilled) { Color.White } else { AccentBlue }

    Box(
        modifier = Modifier
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        LikeComponent(
            isLiked = isFilled,
            onLike = {}
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
            onClick = {},
        )
        FavouriteToolbarComponent(
            count = 17,
            isFilled = false,
            onClick = {},
        )
        FavouriteToolbarComponent(
            count = 100,
            isFilled = true,
            onClick = {},
        )
        FavouriteToolbarComponent(
            count = 100,
            isFilled = false,
            onClick = {},
        )
    }
}