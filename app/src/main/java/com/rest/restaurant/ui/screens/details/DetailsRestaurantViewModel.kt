package com.rest.restaurant.ui.screens.details

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.R
import com.rest.restaurant.domain.usecases.GetSingleRestaurantUseCase
import com.rest.restaurant.domain.usecases.UpdateLikeRestaurantUseCase
import com.rest.restaurant.navigation.Screen
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
class DetailsRestaurantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
    private val getSingleRestaurantUseCase: GetSingleRestaurantUseCase,
    private val updateLikeRestaurantUseCase: UpdateLikeRestaurantUseCase,
): ViewModel() {

    val restaurantId = savedStateHandle.getStateFlow(Screen.KEY_RESTAURANT_ID, 0)
    val errorMessageLiked = MutableStateFlow("")
    private var likeJob: Job? = null

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
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            updateLikeRestaurantUseCase(restaurantId = restaurantId, isLike = isLike).collect { isCorrect ->
                if (!isCorrect) {
                    errorMessageLiked.value = context.getString(R.string.error_like)
                }
            }
        }
    }

}