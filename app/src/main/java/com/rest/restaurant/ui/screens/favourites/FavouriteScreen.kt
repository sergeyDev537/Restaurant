package com.rest.restaurant.ui.screens.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rest.restaurant.ui.screens.restaurants.RestaurantsScreenContent
import com.rest.restaurant.ui.theme.Background
import com.rest.restaurant.ui.widgets.ErrorComponent
import com.rest.restaurant.ui.widgets.LoadingComponent

@Composable
fun FavouriteScreen(
    innerPadding: PaddingValues,
    viewModel: FavouriteViewModel = hiltViewModel(),
    onRestaurantClick: (Int) -> Unit
) {

    val state = viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Background),
        contentAlignment = Alignment.Center,
    ) {
        when(val currentState = state.value) {
            is FavouriteScreenState.Loading -> {
                LoadingComponent()
            }
            is FavouriteScreenState.Success -> {
                RestaurantsScreenContent(
                    restaurants = currentState.restaurants,
                    onRestaurantClick = onRestaurantClick,
                    onLike = { id, isLike ->
                        viewModel.updateLike(restaurantId = id, isLike = isLike)
                    }
                )
            }
            is FavouriteScreenState.Error -> {
                ErrorComponent(
                    message = currentState.errorMessage
                )
            }
            is FavouriteScreenState.Initial -> {}
        }
    }

}