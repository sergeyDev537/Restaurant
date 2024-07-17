package com.rest.restaurant.ui.screens.details

import com.rest.restaurant.domain.entity.DetailsRestaurant

sealed class DetailsScreenState {
    data object Loading : DetailsScreenState()
    data class Success(val restaurant: DetailsRestaurant) : DetailsScreenState()
    data class Error(val errorMessage: String) : DetailsScreenState()
    data object Initial : DetailsScreenState()
}