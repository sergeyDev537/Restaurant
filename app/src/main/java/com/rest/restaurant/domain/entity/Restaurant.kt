package com.rest.restaurant.domain.entity

data class Restaurant(
    val id: Long,
    val imageUrl: String,
    val rating: Float,
    val averageBill: Int,
    val currencyType: String,
    val typeDishes: String,
    val isLike: Boolean,
    val description: String,
)
