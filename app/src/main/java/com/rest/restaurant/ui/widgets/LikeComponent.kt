package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rest.restaurant.R

@Composable
fun LikeComponent(
    isLiked: Boolean,
    onLike: (Boolean) -> Unit,
) {

    val imageIdRes = if (isLiked) { R.drawable.ic_like_filled } else { R.drawable.ic_like_unfilled }

    Image(
        modifier = Modifier
            .size(24.dp)
            .clickable {
                onLike(!isLiked)
            },
        contentScale = ContentScale.FillBounds,
        painter = painterResource(id = imageIdRes),
        contentDescription = "Like"
    )

}

@Preview
@Composable
fun LikeComponentPreview() {
    Row {
        LikeComponent(
            isLiked = true,
            onLike = {}
        )
        Spacer(modifier = Modifier.width(15.dp))
        LikeComponent(
            isLiked = false,
            onLike = {}
        )
    }
}