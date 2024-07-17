package com.rest.restaurant.ui.widgets

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.rest.restaurant.ui.theme.AccentBlue

@Composable
fun LoadingComponent() {
    CircularProgressIndicator(
        color = AccentBlue,
    )
}