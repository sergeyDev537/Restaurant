package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rest.restaurant.ui.theme.AccentBlue

@Composable
fun RatingComponent(
    rating: Float,
    textColor: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            tint = AccentBlue,
            contentDescription = rating.toString()
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating.toString(),
            fontSize = 15.sp,
            color = textColor,
        )
    }
}

@Preview
@Composable
fun RatingComponentPreview() {
    RatingComponent(
        rating = 4.5F,
        textColor = Color.White
    )
}