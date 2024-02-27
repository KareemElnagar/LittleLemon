package com.kareem.littlelemon.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kareem.littlelemon.MenuViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DishDetails(navController: NavHostController) {
    val vm: MenuViewModel = viewModel()
    val databaseMenuItem = vm.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    for (dish in databaseMenuItem) {
        if (dish.id == vm.selectedDish) {
            Column(modifier = Modifier.fillMaxSize()) {
                GlideImage(model = dish.image, contentDescription = dish.description)
                Text(text = dish.title)
                Text(text = dish.description)
                Text(text = "${dish.price}")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = { }
                    ) {
                        Text(text = "Add for ${dish.price}")
                    }
                }

            }
        } else {
            Text(text = "Dish is Unavailable")
        }
    }

}