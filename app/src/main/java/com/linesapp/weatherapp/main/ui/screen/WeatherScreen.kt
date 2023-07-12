package com.linesapp.weatherapp.main.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.weatherapp.R
import com.linesapp.weatherapp.main.model.WeatherResponse
import com.linesapp.weatherapp.main.model.forecast_model.ForecastResponse
import com.linesapp.weatherapp.main.ui.ForecastItem
import retrofit2.Response

@Composable
fun WeatherScreen(
    current:  State<Response<WeatherResponse>?>,
    onNavigateToSearch:() -> Unit,
    onNavigate:() -> Unit,
    forecastState: State<Response<ForecastResponse>?>
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEEB),
                        Color(0xFFB0C4DE),
                        Color(0xFF3C3F41)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable(
                    onClick = onNavigateToSearch
                )
                .height(50.dp)
        ){
            Row {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search icon",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(90.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    text = "search location",
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(5f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                current.value?.body()?.location?.let {
                    it.name?.let { it1 ->
                        Text(
                            text = it1,
                            fontSize = 30.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                AsyncImage(
                    modifier = Modifier
                        .size(90.dp),
                    model = "https:${current.value?.body()?.current?.condition?.icon}",
                    contentDescription = "icon"
                )

                Text(
                    modifier = Modifier
                        .clickable(
                            onClick = onNavigate
                        ),
                    text = current.value?.body()?.current?.temp_c?.toInt().toString(),
                    fontSize = 120.sp,
                    color = Color.White
                )

                Text(
                    text = "Degrees Celsius",
                    fontSize = 15.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                current.value?.body()?.current?.condition?.let {
                    Text(
                        text = it.text,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            LazyRow{
                val forecast = forecastState.value?.body()?.forecast?.forecastday
                forecast?.size?.let {
                    items(it){ index ->
                        val item = forecast[index].hour[index]
                        ForecastItem(
                            icon = "https:${item.condition.icon}",
                            temp = item.temp_c.toString()+"°C",
                            hour = item.time.substring(12, 16)
                        )
                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
            }
        }
    }
}