package com.rest.restaurant.data.network.pdo

data class ShortRestaurantPdo(
    val averageCheck: List<Any>,
    val cuisines: List<String>,
    val distance: Any,
    val id: Int,
    val isFavorite: Boolean,
    val name: String,
    val photo: String,
    val rate: Double
)