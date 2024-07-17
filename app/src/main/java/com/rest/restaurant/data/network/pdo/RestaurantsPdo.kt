package com.rest.restaurant.data.network.pdo

import com.google.gson.annotations.SerializedName

data class RestaurantsPdo(
    @SerializedName("data")
    val restaurants: List<ShortRestaurantPdo>,
    @SerializedName("meta")
    val meta: Meta
)