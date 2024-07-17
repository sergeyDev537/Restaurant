package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountLikesRestaurantUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(): Flow<Int> {
        return restaurantRepo.getCountLikesRestaurant()
    }

}