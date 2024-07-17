package com.rest.restaurant.ui.screens.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.domain.usecases.GetRestaurantsUseCase
import com.rest.restaurant.domain.usecases.UpdateLikeRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val updateLikeRestaurantUseCase: UpdateLikeRestaurantUseCase,
) : ViewModel() {

    val state = getRestaurantsUseCase()
        .map { RestaurantsScreenState.Success(it) as RestaurantsScreenState }
        .onStart { emit(RestaurantsScreenState.Loading) }
        .catch { emit(RestaurantsScreenState.Error(it.message ?: "")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = RestaurantsScreenState.Initial
        )

    fun updateLike(restaurantId: Int, isLike: Boolean) {
        updateLikeRestaurantUseCase(restaurantId = restaurantId, isLike = isLike)
    }

}