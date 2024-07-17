package com.rest.restaurant.domain.repos

import com.rest.restaurant.domain.entity.Restaurant

interface RestaurantRepo {

    fun getRestaurants(): List<Restaurant>

    fun getSingleRestaurant(restaurantId: Int): Restaurant

    fun updateLikeRestaurant(restaurantId: Int, isLike: Boolean): Boolean

    fun getCountLikesRestaurant(): Int

}