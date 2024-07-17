package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import javax.inject.Inject

class GetSingleRestaurantUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(restaurantId: Int): Restaurant {
        return restaurantRepo.getSingleRestaurant(restaurantId = restaurantId)
    }

}