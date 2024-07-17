package com.rest.restaurant.navigation

import android.net.Uri

sealed class Screen(
    val route: String,
) {

    data object Restaurants : Screen(ROUTE_RESTAURANTS)
    data object SingleRestaurant : Screen(ROUTE_SINGLE_RESTAURANT) {
        private const val ROUTE_FOR_ARGS = "restaurant"

        fun getRouteWithArgs(restaurantId: Int): String {
            return "$ROUTE_FOR_ARGS/${restaurantId.encode()}"
        }
    }

    data object Favourite : Screen(ROUTE_FAVOURITE)

    companion object {

        const val KEY_RESTAURANT_ID = "restaurantId"

        const val ROUTE_RESTAURANTS = "restaurants"
        const val ROUTE_SINGLE_RESTAURANT = "restaurant/{$KEY_RESTAURANT_ID}"
        const val ROUTE_FAVOURITE = "favourite"
    }
}

fun Int.encode(): String {
    return Uri.encode(this.toString())
}