package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rest.restaurant.ui.theme.AccentBlue

@Composable
fun LikeComponent(
    isLiked: Boolean,
    onLike: (Boolean) -> Unit,
) {

    val imageVector = if (isLiked) { Icons.Filled.Favorite } else { Icons.Filled.FavoriteBorder }

    Box(
        modifier = Modifier
            .clickable {
                onLike(!isLiked)
            },
    ) {
        IconLikeComponent(
            icon = imageVector
        )
    }

}

@Composable
fun IconLikeComponent(
    icon: ImageVector,
) {
    Icon(
        modifier = Modifier
            .size(24.dp),
        imageVector = icon,
        tint = AccentBlue,
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