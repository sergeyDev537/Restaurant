package com.rest.restaurant.data.impl

import com.rest.restaurant.data.managers.StorageLocale
import com.rest.restaurant.data.mapper.RestaurantsMapper
import com.rest.restaurant.data.network.RestaurantApi
import com.rest.restaurant.domain.entity.DetailsRestaurant
import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepoImpl @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val mapper: RestaurantsMapper,
    private val storage: StorageLocale,
) : RestaurantRepo {

    override fun getRestaurants(): Flow<List<Restaurant>> = flow {
        val response = restaurantApi.getRestaurants()
        if (response.body() != null && response.isSuccessful) {
            emit(mapper.mapRestaurantsPdoToEntity(response.body()!!))
        } else {
            throw Throwable(message = response.errorBody()?.string())
        }
    }

    override fun getSingleRestaurant(restaurantId: Int): Flow<DetailsRestaurant> = flow {
        val response = restaurantApi.getRestaurant(restaurantId = restaurantId)
        if (response.body() != null && response.isSuccessful) {
            emit(mapper.mapSingleRestaurantPdoToEntity(response.body()!!))
        } else {
            throw Throwable(message = response.errorBody()?.string())
        }
    }

    override fun updateLikeRestaurant(restaurantId: Int, isLike: Boolean): Flow<Boolean> = flow {
        emit(true)
        val response = if (isLike) {
            restaurantApi.setFavourite(restaurantId = restaurantId)
        } else {
            restaurantApi.removeFavourite(restaurantId = restaurantId)
        }
        emit(response.isSuccessful && response.code() == 201)
    }

    override fun getCountLikesRestaurant(): Flow<Int> = flow {
        emit(storage.countFavourites)
    }
}