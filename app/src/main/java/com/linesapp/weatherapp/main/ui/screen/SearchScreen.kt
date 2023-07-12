package com.linesapp.weatherapp.main.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.linesapp.weatherapp.R
import com.linesapp.weatherapp.main.model.search_model.ResponseSearchItem
import com.linesapp.weatherapp.main.ui.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: WeatherViewModel,
    navController: NavController,
    sharedString: MutableState<String>
){
    val searchQuery by remember { mutableStateOf(sharedString) }

    var isLoading by remember(searchQuery) {
        mutableStateOf(false)
    }


    val coroutineScope = rememberCoroutineScope()

    var searchItems by remember (searchQuery){
        mutableStateOf(emptyList<ResponseSearchItem>())
            .apply {
                coroutineScope.launch {
                    delay(3000)
                    if(searchQuery.value.isNotBlank()){
                        isLoading = true
                        value = viewModel.searchWeatherFlow(searchQuery.value).first()
                        isLoading = false
                    }
                }
            }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
//                        Color.White,
                        Color(0xFF87CEEB), // Sky blue
                        Color(0xFFB0C4DE),  // Soft blue-gray
                        Color(0xFF3C3F41)
                    )
                )
            )
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            value = searchQuery.value ,
            onValueChange = {
                searchQuery.value = it
                coroutineScope.launch {
                    delay(500)
                    if(it == searchQuery.value && searchQuery.value.isNotBlank()){
                        isLoading = true
                        searchItems = viewModel.searchWeatherFlow(searchQuery.value).first()
                        isLoading = false
                    }
                }
            },
            placeholder = {
                Text(
                    text = "search"
                )
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "search icon")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (isLoading) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(16.dp)
                )
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
            ){
                items(searchItems.size){ index ->
                    val searchLocation = searchItems[index]

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                navController.navigate("weather_screen")
                                searchQuery.value = searchLocation.name
                            }
                    ) {
                        Text(
                            modifier = Modifier,
                            text = searchLocation.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = searchLocation.country,
                            fontSize = 15.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun SearchScreenPreview(){
//    SearchScreen()
//}