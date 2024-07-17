package com.rest.restaurant.domain.entity

data class DetailsRestaurant(
    val id: Int,
    val name: String,
    val imageUrls: List<String>,
    val rating: Float,
    val averageBill: Any?,
    val currencyType: String,
    val typeDishes: String,
    val isLike: Boolean,
    val description: String,
)
