package com.rest.restaurant.data.network.pdo

data class RestaurantPdo(
    val averageCheck: List<Any>,
    val categoryName: String,
    val cuisines: List<Any>,
    val detailedInfo: String,
    val discount: Int,
    val distance: Any,
    val email: Any,
    val id: Int,
    val isFavorite: Boolean,
    val location: Location,
    val name: String,
    val phones: List<String>,
    val photos: List<String>,
    val rate: Double,
    val rateCount: Int,
    val review: Any,
    val reviewCount: Int,
    val schedule: List<Schedule>,
    val serviceLanguages: List<String>,
    val services: List<Any>,
    val socials: List<Social>,
    val urls: List<String>
)