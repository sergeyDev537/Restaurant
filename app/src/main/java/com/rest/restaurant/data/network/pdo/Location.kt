package com.rest.restaurant.data.network.pdo

data class Location(
    val address: String,
    val city: String,
    val district: Int,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val organization: Int
)