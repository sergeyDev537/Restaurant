package com.rest.restaurant.data.network.pdo

data class Meta(
    val currentPage: Int,
    val pageCount: Int,
    val perPage: Int,
    val totalCount: Int
)