package com.rest.restaurant.domain.repos

import com.rest.restaurant.domain.entity.DetailsRestaurant
import com.rest.restaurant.domain.entity.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepo {

    fun getRestaurants(): Flow<List<Restaurant>>

    fun getFavouriteRestaurants(): Flow<List<Restaurant>>

    fun getSingleRestaurant(restaurantId: Int): Flow<DetailsRestaurant>

    fun updateLikeRestaurant(restaurantId: Int, isLike: Boolean): Flow<Boolean>

    fun getCountLikesRestaurant(): Flow<Int>

}