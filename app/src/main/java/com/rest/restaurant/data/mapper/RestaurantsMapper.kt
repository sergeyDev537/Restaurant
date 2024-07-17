package com.rest.restaurant.data.mapper

import com.rest.restaurant.data.network.pdo.RestaurantPdo
import com.rest.restaurant.data.network.pdo.ShortRestaurantPdo
import com.rest.restaurant.data.network.pdo.RestaurantsPdo
import com.rest.restaurant.domain.entity.DetailsRestaurant
import com.rest.restaurant.domain.entity.Restaurant
import javax.inject.Inject

class RestaurantsMapper @Inject constructor() {

    fun mapRestaurantsPdoToEntity(restaurants: RestaurantsPdo) = restaurants.restaurants.map {
        mapRestaurantPdoToEntity(it)
    }

    fun mapRestaurantPdoToEntity(pdo: ShortRestaurantPdo) = Restaurant(
        id = pdo.id,
        name = pdo.name,
        imageUrl = pdo.photo,
        rating = pdo.rate.toFloat(),
        averageBill = if (pdo.averageCheck.isNullOrEmpty()) null else pdo.averageCheck.firstOrNull(),
        currencyType = "€",
        typeDishes = pdo.cuisines.joinToString(","),
        isLike = pdo.isFavorite,
    )

    fun mapSingleRestaurantPdoToEntity(pdo: RestaurantPdo) = DetailsRestaurant(
        id = pdo.id,
        name = pdo.name,
        imageUrls = pdo.photos,
        rating = pdo.rate.toFloat(),
        averageBill = if (pdo.averageCheck.isNullOrEmpty()) null else pdo.averageCheck.firstOrNull(),
        currencyType = "€",
        typeDishes = pdo.cuisines.joinToString(","),
        isLike = pdo.isFavorite,
        description = pdo.detailedInfo,
    )

}