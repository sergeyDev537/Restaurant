package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderRestaurantComponent(
    name: String,
    isLiked: Boolean,
    textSize: TextUnit = 17.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onLike: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1F),
            text = name,
            color = Color.Black,
            fontSize = textSize,
            fontWeight = fontWeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        LikeComponent(
            isLiked = isLiked,
            onLike = onLike
        )
    }
}

@Preview
@Composable
fun HeaderRestaurantComponentPreview() {
    Column {
        HeaderRestaurantComponent(
            name = "Piatsa Gourounaki piatsa gourounaki piatsa gourounaki",
            isLiked = true,
            textSize = 17.sp,
            fontWeight = FontWeight.Normal,
            onLike = {}
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderRestaurantComponent(
            name = "Piatsa Gourounaki piatsa gourounaki piatsa gourounaki",
            isLiked = false,
            textSize = 20.sp,
            fontWeight = FontWeight.Bold,
            onLike = {}
        )
    }
}