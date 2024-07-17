package com.rest.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rest.restaurant.R
import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.ui.theme.Gray
import com.rest.restaurant.ui.widgets.HeaderRestaurantComponent
import com.rest.restaurant.ui.widgets.RatingComponent
import com.rest.restaurant.ui.widgets.SumCurrencyComponent

@Composable
fun RestaurantsScreen() {
    //TODO parse state
}

@Composable
fun RestaurantsScreenContent(
    restaurants: List<Restaurant>,
    onLike: (Long, Boolean) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restaurants) { restaurant ->
            RestaurantCard(
                restaurant = restaurant,
                onLike = { newLikeStatus ->
                    onLike(restaurant.id, newLikeStatus)
                }
            )
        }
    }
}

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onLike: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
    ) {
        //TODO Change To AsyncImage
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            contentDescription = restaurant.name,
        )
        Column(
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 16.dp)
        ) {
            HeaderRestaurantComponent(
                name = restaurant.name,
                isLiked = restaurant.isLike,
                onLike = onLike,
            )
            Spacer(modifier = Modifier.height(10.dp))
            RestaurantInfoLayout(
                rating = restaurant.rating,
                currencyType = restaurant.currencyType,
                averageBill = restaurant.averageBill.toString(),
                typeDishes = restaurant.typeDishes
            )
        }
    }
}

@Composable
fun RestaurantInfoLayout(
    rating: Float,
    currencyType: String,
    averageBill: String,
    typeDishes: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RatingComponent(
            rating = rating,
            textColor = Color.Black,
        )
        Spacer(modifier = Modifier.width(8.dp))
        SumCurrencyComponent(
            sum = averageBill,
            currency = currencyType,
            textColor = Gray,
            textSize = 15.sp,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = typeDishes,
            color = Gray,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun RestaurantInfoLayoutPreview() {
    RestaurantInfoLayout(
        rating = 4.5F,
        currencyType = "€",
        averageBill = "1000",
        typeDishes = "Суши, Пицца, Завтраки, Американская, Грузинская"
    )
}

@Preview
@Composable
fun RestaurantCardPreview() {
    val restaurant = Restaurant(
        id = 0L,
        name = "Aster Bakery",
        imageUrl = "",
        rating = 4.5F,
        averageBill = 1000,
        currencyType = "€",
        typeDishes = "Суши, Пицца, Завтраки, Американская, Грузинская",
        isLike = true,
        description = ""
    )
    RestaurantCard(
        restaurant = restaurant,
        onLike = {},
    )
}

@Preview
@Composable
fun RestaurantsScreenContentPreview() {
    val restaurant = Restaurant(
        id = 0L,
        name = "Aster Bakery",
        imageUrl = "",
        rating = 4.5F,
        averageBill = 1000,
        currencyType = "€",
        typeDishes = "Суши, Пицца, Завтраки, Американская, Грузинская",
        isLike = true,
        description = ""
    )
    val restaurants = listOf(restaurant, restaurant, restaurant, restaurant, restaurant, restaurant, restaurant, restaurant)
    RestaurantsScreenContent(
        restaurants = restaurants,
        onLike = { id, newLikeStatus ->

        }
    )
}