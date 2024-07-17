package com.rest.restaurant.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.domain.usecases.GetRestaurantsUseCase
import com.rest.restaurant.domain.usecases.GetSingleRestaurantUseCase
import com.rest.restaurant.domain.usecases.UpdateLikeRestaurantUseCase
import com.rest.restaurant.ui.screens.restaurants.RestaurantsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsRestaurantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSingleRestaurantUseCase: GetSingleRestaurantUseCase,
    private val updateLikeRestaurantUseCase: UpdateLikeRestaurantUseCase,
): ViewModel() {

    val restaurantId = savedStateHandle.getLiveData<Int>("restaurantId")

    val state = getSingleRestaurantUseCase(restaurantId = restaurantId.value ?: 0)
        .map { DetailsScreenState.Success(it) as DetailsScreenState }
        .onStart { emit(DetailsScreenState.Loading) }
        .catch { emit(DetailsScreenState.Error(it.message ?: "")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = DetailsScreenState.Initial
        )

    fun updateLike(restaurantId: Int, isLike: Boolean) {
        updateLikeRestaurantUseCase(restaurantId = restaurantId, isLike = isLike)
    }

}