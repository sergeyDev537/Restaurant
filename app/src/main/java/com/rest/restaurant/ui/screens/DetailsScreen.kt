package com.rest.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rest.restaurant.R
import com.rest.restaurant.domain.entity.Restaurant
import com.rest.restaurant.ui.theme.AccentBlue
import com.rest.restaurant.ui.theme.Gray
import com.rest.restaurant.ui.widgets.HeaderRestaurantComponent
import com.rest.restaurant.ui.widgets.RatingBar

@Composable
fun DetailsScreen() {

}

@Composable
fun DetailsScreenContent(
    restaurant: Restaurant,
    onLike: (Long, Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(26.dp)),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            contentDescription = restaurant.name,
        )
        Spacer(modifier = Modifier.height(8.dp))
        HeaderDetailsRestaurant(
            name = restaurant.name,
            isLiked = restaurant.isLike,
            rating = restaurant.rating,
            distance = "",
            onLike = { isLike ->
                onLike(restaurant.id, isLike)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionComponent(
            description = restaurant.description
        )
    }
}

@Composable
private fun HeaderDetailsRestaurant(
    name: String,
    isLiked: Boolean,
    rating: Float,
    distance: String,
    onLike: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        HeaderRestaurantComponent(
            name = name,
            isLiked = isLiked,
            textSize = 20.sp,
            fontWeight = FontWeight.Bold,
            onLike = onLike,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            RatingBarWithCount(
                modifier = Modifier.weight(1F),
                rating = rating
            )
            Text(
                text = distance,
                fontSize = 15.sp,
                color = Gray,
                textAlign = TextAlign.End,
            )
        }
    }
}

@Composable
fun RatingBarWithCount(
    modifier: Modifier = Modifier,
    rating: Float,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RatingBar(
            rating = rating
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = rating.toString(),
            color = AccentBlue,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun DescriptionComponent(
    description: String,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append(stringResource(R.string.description))
                }
                withStyle(SpanStyle()) {
                    append("\n\n")
                }
                withStyle(SpanStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Gray)) {
                    append(description)
                }
            }
        )
    }
}

@Preview
@Composable
fun DetailsScreenContentPreview() {
    val restaurant = Restaurant(
        id = 0L,
        name = "Aster Bakery",
        imageUrl = "",
        rating = 4.5F,
        averageBill = 1000,
        currencyType = "€",
        typeDishes = "Суши, Пицца, Завтраки, Американская, Грузинская",
        isLike = true,
        description = "Aster Bakery — это кафе-пекарня с открытой кухней, где посетители могут наблюдать за процессом приготовления хлеба и выпечки.Меню включает в себя разнообразные завтраки, такие как скрэмбл, вареные яйца и авокадо-тосты, а также основное меню с блюдами, такими как ризотто и капкейки. Гости высоко оценивают выпечку, особенно шоколадный торт и краффин. Цены в кафе средние."
    )

    DetailsScreenContent(
        restaurant = restaurant,
        onLike = { id, isLike ->

        }
    )
}