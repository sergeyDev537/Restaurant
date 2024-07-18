package com.rest.restaurant.ui.screens.favourites

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.R
import com.rest.restaurant.domain.usecases.GetFavouriteRestaurantsUseCase
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
class FavouriteViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getFavouriteRestaurantsUseCase: GetFavouriteRestaurantsUseCase,
    private val updateLikeRestaurantUseCase: UpdateLikeRestaurantUseCase,
) : ViewModel() {

    val errorMessageLiked = MutableStateFlow("")
    private var likeJob: Job? = null

    val state = getFavouriteRestaurantsUseCase()
        .map { FavouriteScreenState.Success(it) as FavouriteScreenState }
        .onStart { emit(FavouriteScreenState.Loading) }
        .catch { emit(FavouriteScreenState.Error(it.message ?: "")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = FavouriteScreenState.Initial
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