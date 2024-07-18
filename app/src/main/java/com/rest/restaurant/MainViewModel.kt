package com.rest.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rest.restaurant.domain.usecases.GetCountLikesRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountLikesRestaurantUseCase: GetCountLikesRestaurantUseCase,
) : ViewModel() {

    val countLikes = getCountLikesRestaurantUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = 0
        )

}