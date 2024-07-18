package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteRestaurantsUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(): Flow<List<Restaurant>> {
        return restaurantRepo.getFavouriteRestaurants()
    }

}