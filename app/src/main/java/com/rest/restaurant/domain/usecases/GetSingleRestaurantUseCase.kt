package com.rest.restaurant.domain.usecases

import com.rest.restaurant.domain.entity.DetailsRestaurant
import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleRestaurantUseCase @Inject constructor(private val restaurantRepo: RestaurantRepo) {

    operator fun invoke(restaurantId: Int): Flow<DetailsRestaurant> {
        return restaurantRepo.getSingleRestaurant(restaurantId = restaurantId)
    }

}