package com.rest.restaurant.ui.screens.restaurants

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.R
import com.rest.restaurant.domain.usecases.GetRestaurantsUseCase
import com.rest.restaurant.domain.usecases.UpdateLikeRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val updateLikeRestaurantUseCase: UpdateLikeRestaurantUseCase,
) : ViewModel() {

    val errorMessageLiked = MutableStateFlow("")
    private var likeJob: Job? = null

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
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            updateLikeRestaurantUseCase(restaurantId = restaurantId, isLike = isLike)
                .collect { isCorrect ->
                    if (!isCorrect) { setErrorLiked() }
                }
        }
    }

    private fun setErrorLiked() {
        errorMessageLiked.value = context.getString(R.string.error_like)
    }

}