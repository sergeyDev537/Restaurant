package com.rest.restaurant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rest.restaurant.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NavigationState(
    val navHostController: NavHostController,
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSingleRestaurant(restaurantId: Int) {
        navHostController.navigate(Screen.SingleRestaurant.getRouteWithArgs(restaurantId))
    }

    fun isScreenFavouriteFlow(): Flow<Boolean> {
        return navHostController.currentBackStackEntryFlow.map { it.destination.route == Screen.ROUTE_FAVOURITE }
    }

    fun isScreenFavourite() = navHostController.currentBackStackEntry?.destination?.route == Screen.ROUTE_FAVOURITE

    fun getTitleToolbar(): Flow<Int> {
        return navHostController.currentBackStackEntryFlow.map { getTitle(it.destination.route) }
    }

    private fun getTitle(route: String?): Int {
        return when(route) {
            Screen.ROUTE_SINGLE_RESTAURANT -> { R.string.restaurant }
            Screen.ROUTE_FAVOURITE -> { R.string.favourite }
            else -> { R.string.restaurants }
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController(),
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}