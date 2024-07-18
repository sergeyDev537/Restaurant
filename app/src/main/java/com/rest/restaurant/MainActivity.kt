package com.rest.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rest.restaurant.navigation.AppNavGraph
import com.rest.restaurant.navigation.Screen
import com.rest.restaurant.navigation.rememberNavigationState
import com.rest.restaurant.ui.screens.details.DetailsScreen
import com.rest.restaurant.ui.screens.favourites.FavouriteScreen
import com.rest.restaurant.ui.screens.restaurants.RestaurantsScreen
import com.rest.restaurant.ui.theme.RestaurantTheme
import com.rest.restaurant.ui.widgets.FavouriteToolbarComponent
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val navigationState = rememberNavigationState()

                val countLikes = viewModel.countLikes.collectAsState()
                val titleToolbarResId = navigationState.getTitleToolbar().collectAsState(R.string.restaurants)
                val screenIsFavourite = navigationState.isScreenFavouriteFlow().collectAsState(false)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = stringResource(titleToolbarResId.value),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            actions = {
                                IconButton(
                                    content = {
                                        FavouriteToolbarComponent(
                                            count = countLikes.value,
                                            isFilled = screenIsFavourite.value,
                                        )
                                    },
                                    onClick = {
                                        if (!navigationState.isScreenFavourite()) {
                                            navigationState.navigateTo(Screen.ROUTE_FAVOURITE)
                                        } else {
                                            navigationState.navHostController.popBackStack()
                                        }

                                    }
                                )

                            }
                        )
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        navHostController = navigationState.navHostController,
                        restaurantsScreenContent = {
                            RestaurantsScreen(
                                innerPadding = innerPadding,
                                onRestaurantClick = { id ->
                                    navigationState.navigateToSingleRestaurant(restaurantId = id)
                                },
                            )
                        },
                        singleRestaurantScreenContent = { restaurantId ->
                            DetailsScreen(
                                innerPadding = innerPadding,
                                restaurantId = restaurantId,
                            )
                        },
                        favouriteScreenContent = {
                            FavouriteScreen(
                                innerPadding = innerPadding,
                                onRestaurantClick = { id ->
                                    navigationState.navigateToSingleRestaurant(restaurantId = id)
                                }
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestaurantTheme {
        Greeting("Android")
    }
}