package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(): List<Restaurant> {
        return restaurantRepo.getRestaurants()
    }

}