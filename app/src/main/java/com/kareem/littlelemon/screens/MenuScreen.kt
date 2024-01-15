package com.kareem.littlelemon.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kareem.littlelemon.MenuItemRoom
import com.kareem.littlelemon.MenuViewModel

@Composable
fun MenuScreen(navController: NavHostController) {
    val vm: MenuViewModel = viewModel()
    val databaseMenuItem = vm.getAllDatabaseMenuItems().observeAsState(emptyList()).value

    LaunchedEffect(key1 = "Fetching_menu", block = {
        try {
            vm.fetchMenuIfNeeded()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    })
    Column(Modifier.fillMaxSize()) {

        MenuGrid(databaseMenuItem = databaseMenuItem)
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuGrid(databaseMenuItem: List<MenuItemRoom>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(databaseMenuItem) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable {

                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    model = it.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(160.dp)
                )
                Text(text = it.title)
            }

        }


    }
}