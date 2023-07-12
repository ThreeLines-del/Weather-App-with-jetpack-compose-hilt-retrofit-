package com.linesapp.weatherapp.main.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.weatherapp.R
import com.linesapp.weatherapp.main.model.WeatherResponse
import com.linesapp.weatherapp.main.ui.WeatherItem
import retrofit2.Response

@Composable
fun WeatherDetailScreen(
    current: State<Response<WeatherResponse>?>
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3C3F41),
                        Color(0xFFB0C4DE),
                        Color(0xFF87CEEB)
                    )
                )
            )
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
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

                    current.value?.body()?.location?.let {
                        it.country?.let { it1 ->
                            Text(
                                text = it1,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }
                    current.value?.body()?.location?.let {
                        it.region?.let { it1 ->
                            Text(
                                text = it1,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }
                    current.value?.body()?.location?.let {
                        it.localtime?.let { it1 ->
                            Text(
                                text = it1,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }

                }
            }
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                model = "https:${current.value?.body()?.current?.condition?.icon}",
                contentDescription = "current icon"
            )
            LazyColumn(
                modifier = Modifier
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                items(1){
                    current.value?.body()?.current?.let { it1 ->
                        WeatherItem(
                            weatherItem = "Humidity",
                            weatherItemValue = it1.humidity.toString(),
                            weatherItemIcon = painterResource(id = R.drawable.humidity)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Temperature F",
                            weatherItemValue = it1.temp_f.toString()+" f",
                            weatherItemIcon = painterResource(id = R.drawable.thermometer)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Cloud",
                            weatherItemValue = it1.cloud.toString(),
                            weatherItemIcon = painterResource(id = R.drawable.cloud)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Precipitation",
                            weatherItemValue = it1.precip_in.toString()+" in",
                            weatherItemIcon = painterResource(id = R.drawable.rain)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Pressure",
                            weatherItemValue = it1.pressure_in.toString()+" in",
                            weatherItemIcon = painterResource(id = R.drawable.barometer)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Uv",
                            weatherItemValue = it1.uv.toString(),
                            weatherItemIcon = painterResource(id = R.drawable.ultraviolet)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Visibility",
                            weatherItemValue = it1.vis_km.toString()+" km",
                            weatherItemIcon = painterResource(id = R.drawable.visibility)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Wind speed",
                            weatherItemValue = it1.wind_kph.toString()+" kph",
                            weatherItemIcon = painterResource(id = R.drawable.wind)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        WeatherItem(
                            weatherItem = "Wind direction",
                            weatherItemValue = it1.wind_dir,
                            weatherItemIcon = painterResource(id = R.drawable.vane)
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun WeatherDetailScreenPreview(){
//    WeatherDetailScreen()
//}