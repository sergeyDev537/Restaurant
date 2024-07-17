package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateLikeRestaurantUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(restaurantId: Int, isLike: Boolean): Flow<Boolean> {
        return restaurantRepo.updateLikeRestaurant(restaurantId = restaurantId, isLike = isLike)
    }

}