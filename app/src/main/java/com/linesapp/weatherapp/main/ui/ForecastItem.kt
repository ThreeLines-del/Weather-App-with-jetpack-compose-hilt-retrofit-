package com.linesapp.weatherapp.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ForecastItem(
    icon: Any?,
    temp: String,
    hour: String
){
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(100.dp)
    ){
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hour,
                fontSize = 20.sp,
                color = Color.White
            )

            AsyncImage(
                modifier = Modifier
                    .size(65.dp)
                    .weight(2f),
                model = icon,
                contentDescription = "Icon"
            )
            Text(
                text = temp,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

//@Composable
//@Preview
//fun ForecastItemPreview(){
//    ForecastItem()
//}