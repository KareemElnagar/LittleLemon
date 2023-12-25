package com.kareem.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kareem.littlelemon.MenuItemDao
import com.kareem.littlelemon.MenuItemRoom
import com.kareem.littlelemon.MenuViewModel
import com.kareem.littlelemon.R
import com.kareem.littlelemon.ui.theme.PrimaryGreen
import com.kareem.littlelemon.ui.theme.PrimaryYellow
import com.kareem.littlelemon.ui.theme.Shapes
import com.kareem.littlelemon.util.Profile
import java.util.Locale.Category


@Composable
fun Home(navController: NavHostController) {
    Column {
        Header(navController)
        HomePage()
    }

}

@Composable
fun Header(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(64.dp)
            .padding(start = 5.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.CenterVertically),

            )
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    navController.navigate(Profile.route)
                }
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val vm: MenuViewModel = viewModel()
    val databaseMenuItem = vm.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val searchPhrase = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = "Fetching_menu", block = {
        try {
            vm.fetchMenuIfNeeded()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    })


    Column(
        modifier = Modifier
            .background(PrimaryGreen)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryYellow
        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            color = Color(0xFFEDEFEE)
        )
        Row(
            modifier = Modifier
                .padding(top = 18.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                color = Color(0xFFEDEFEE),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
            )
        }

        TextField(
            value = searchPhrase.value,
            onValueChange = { searchPhrase.value = it },
            placeholder = { Text(text = "Search Item") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = Shapes.large,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") }

        )

    }
    val filteredMenuItems = if (searchPhrase.value.isBlank()) {
        databaseMenuItem
    } else {
        databaseMenuItem.filter { menuItem ->
            menuItem.title.contains(searchPhrase.value, ignoreCase = true)
        }
    }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "ORDER FOR DELIVERY !", style = MaterialTheme.typography.headlineLarge)

    }
    MenuItems(menuList = filteredMenuItems)
}


@Composable
fun MenuItems(menuList: List<MenuItemRoom>) {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .padding(top = 8.dp, bottom = 8.dp)
    )
    LazyColumn(Modifier.fillMaxHeight()) {
        item {
            for (menuItem in menuList) {
                MenuItem(itemRoom = menuItem)
            }
        }


    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(itemRoom: MenuItemRoom) {
    Spacer(modifier = Modifier.width(8.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Card(colors = CardDefaults.cardColors(Color.White)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(
                    text = itemRoom.title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = itemRoom.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f),
                    color = Color.Gray
                )
                Text(
                    text = "$${itemRoom.price}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 5.dp),
                    fontWeight = FontWeight.Bold
                )

            }
            GlideImage(
                model = itemRoom.image,
                contentDescription = "",
                alignment = Alignment.CenterEnd,
                modifier = Modifier.size(100.dp, 100.dp),
                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.width(8.dp))


        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}