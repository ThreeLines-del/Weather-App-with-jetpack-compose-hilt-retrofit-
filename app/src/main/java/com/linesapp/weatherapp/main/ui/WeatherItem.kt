package com.linesapp.weatherapp.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherItem(
    weatherItem: String,
    weatherItemValue: String,
    weatherItemIcon: Painter

){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB0C4DE),
                        Color(0xFF3C3F41)
                    )
                )
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
                .size(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = weatherItemIcon,
                contentDescription = "Icon",
                tint = Color.White
            )
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 5.dp),
                text = weatherItem,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .weight(1f),
                text = weatherItemValue,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}