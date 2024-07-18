package com.rest.restaurant.data.impl

import com.rest.restaurant.data.managers.StorageLocale
import com.rest.restaurant.data.mapper.RestaurantsMapper
import com.rest.restaurant.data.network.RestaurantApi
import com.rest.restaurant.domain.entity.DetailsRestaurant
import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.domain.repos.RestaurantRepo
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepoImpl @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val mapper: RestaurantsMapper,
    private val storage: StorageLocale,
) : RestaurantRepo {

    val refreshGlobalLike = MutableSharedFlow<Unit>()
    val refreshLocalLike = MutableSharedFlow<Int>()
    val restaurantsLocale = MutableStateFlow<List<Restaurant>>(emptyList())

    override fun getRestaurants(): Flow<List<Restaurant>> = flow {
        val restaurants = getRestaurantsOnApi()
        restaurantsLocale.value = restaurants
        emit(restaurants)

        merge(refreshLocalLike, refreshGlobalLike)
            .buffer(1, BufferOverflow.DROP_OLDEST)
            .collect { value ->
                if (value is Unit) {
                    val restaurantsRefresh = getRestaurantsOnApi()
                    restaurantsLocale.value = restaurantsRefresh
                    emit(restaurantsRefresh)
                } else if (value is Int) {
                    val currentRests = restaurantsLocale.value.toMutableList()
                    val newRests = currentRests.map {
                        if (it.id == value) it.copy(isLike = !it.isLike) else it
                    }
                    restaurantsLocale.value = newRests
                    emit(newRests)
                }
            }
    }

    private suspend fun getRestaurantsOnApi(): List<Restaurant> {
        val response = restaurantApi.getRestaurants()
        if (response.body() != null && response.isSuccessful) {
            return mapper.mapRestaurantsPdoToEntity(response.body()!!)
        } else {
            throw Throwable(message = response.errorBody()?.string())
        }
    }

    override fun getFavouriteRestaurants(): Flow<List<Restaurant>> = flow {
        getRestaurants().collect { restaurants ->
            emit(restaurants.filter { it.isLike })
        }
    }

    override fun getSingleRestaurant(restaurantId: Int): Flow<DetailsRestaurant> = flow {
        emit(getSingleRestaurantsOnApi(restaurantId))
        refreshGlobalLike.collect {
            getSingleRestaurantsOnApi(restaurantId)
        }
    }

    private suspend fun getSingleRestaurantsOnApi(
        restaurantId: Int,
    ): DetailsRestaurant {
        val response = restaurantApi.getRestaurant(restaurantId = restaurantId)
        if (response.body() != null && response.isSuccessful) {
            return mapper.mapSingleRestaurantPdoToEntity(response.body()!!)
        } else {
            throw Throwable(message = response.errorBody()?.string())
        }
    }

    override fun updateLikeRestaurant(restaurantId: Int, isLike: Boolean): Flow<Boolean> = flow {
        val correctResponseCode = if (isLike) RESPONSE_CODE_LIKE_SUCCESS else RESPONSE_CODE_UNLIKE_SUCCESS

        refreshRestaurantsOfLocalLike(restaurantId)
        val response = if (isLike) {
            restaurantApi.setFavourite(restaurantId = restaurantId)
        } else {
            restaurantApi.removeFavourite(restaurantId = restaurantId)
        }
        val result = response.isSuccessful && response.code() == correctResponseCode
        if (result) {
            updateCounterLike(isLike = isLike)
        }
        refreshRestaurantsOfGlobalLike()
        emit(result)
    }.catch {
        refreshRestaurantsOfLocalLike(restaurantId)
        emit(false)
    }

    private suspend fun refreshRestaurantsOfGlobalLike() {
        refreshGlobalLike.emit(Unit)
    }

    private suspend fun refreshRestaurantsOfLocalLike(restaurantId: Int) {
        refreshLocalLike.emit(restaurantId)
    }

    private fun updateCounterLike(isLike: Boolean) {
        val currentCount = storage.countFavourites
        val newCount = (if (isLike) currentCount + 1 else currentCount - 1).coerceAtLeast(minimumValue = 0)
        storage.countFavourites = newCount
    }

    override fun getCountLikesRestaurant(): Flow<Int> = flow {
        emit(storage.countFavourites)
    }

    companion object {
        const val RESPONSE_CODE_LIKE_SUCCESS = 201
        const val RESPONSE_CODE_UNLIKE_SUCCESS = 204
    }
}