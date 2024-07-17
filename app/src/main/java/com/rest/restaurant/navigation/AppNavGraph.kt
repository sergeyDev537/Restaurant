package com.rest.restaurant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    restaurantsScreenContent: @Composable () -> Unit,
    singleRestaurantScreenContent: @Composable (Int) -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Restaurants.route
    ) {
        composable(Screen.Restaurants.route) {
            restaurantsScreenContent()
        }
        composable(
            route = Screen.SingleRestaurant.route,
            arguments = listOf(
                navArgument(Screen.KEY_RESTAURANT_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val restaurantId = it.arguments?.getInt(Screen.KEY_RESTAURANT_ID) ?: throw RuntimeException("Args is null")
            singleRestaurantScreenContent(restaurantId)
        }
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }
    }
}