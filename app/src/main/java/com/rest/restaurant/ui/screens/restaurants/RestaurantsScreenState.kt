package com.rest.restaurant.ui.screens.restaurants

import com.rest.restaurant.domain.entity.Restaurant

sealed class RestaurantsScreenState {
    data object Loading : RestaurantsScreenState()
    data class Success(val restaurants: List<Restaurant>) : RestaurantsScreenState()
    data class Error(val errorMessage: String) : RestaurantsScreenState()
    data object Initial : RestaurantsScreenState()
}