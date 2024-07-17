package com.rest.restaurant.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rest.restaurant.ui.theme.Gray

@Composable
fun SumCurrencyComponent(
    sum: String,
    currency: String,
    textColor: Color,
    textSize: TextUnit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = currency,
            fontSize = textSize,
            color = textColor,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = sum,
            fontSize = textSize,
            color = textColor,
        )
    }

}

@Preview
@Composable
fun SumCurrencyComponentPreview() {
    SumCurrencyComponent(
        sum = "1000",
        currency = "€",
        textColor = Gray,
        textSize = 15.sp
    )
}