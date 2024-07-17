package com.rest.restaurant.domain.entity

data class Restaurant(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val averageBill: Any?,
    val currencyType: String,
    val typeDishes: String?,
    val isLike: Boolean,
)
