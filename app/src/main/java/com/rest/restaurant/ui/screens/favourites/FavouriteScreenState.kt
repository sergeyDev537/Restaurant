package com.rest.restaurant.ui.screens.favourites

import com.rest.restaurant.domain.entity.Restaurant

sealed class FavouriteScreenState {
    data object Loading : FavouriteScreenState()
    data class Success(val restaurants: List<Restaurant>) : FavouriteScreenState()
    data class Error(val errorMessage: String) : FavouriteScreenState()
    data object Initial : FavouriteScreenState()
}