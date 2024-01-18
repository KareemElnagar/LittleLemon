package com.kareem.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kareem.littlelemon.screens.DishDetails
import com.kareem.littlelemon.screens.Home
import com.kareem.littlelemon.screens.MenuScreen
import com.kareem.littlelemon.screens.Onboarding
import com.kareem.littlelemon.screens.Orders
import com.kareem.littlelemon.screens.Profile
import com.kareem.littlelemon.util.Constants
import com.kareem.littlelemon.util.DishDetails
import com.kareem.littlelemon.util.Home
import com.kareem.littlelemon.util.MenuScreen
import com.kareem.littlelemon.util.Onboarding
import com.kareem.littlelemon.util.Orders
import com.kareem.littlelemon.util.Profile

@Composable
fun NavigationComposable(context: Context, navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = determineDestination(context)
    ) {
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(MenuScreen.route){
            MenuScreen(navController)
        }
        composable(Orders.route){
            Orders(navController)
        }
        composable(DishDetails.route){
            DishDetails(navController)
        }



    }
}

fun determineDestination(context: Context): String {
    val sharedPreferences = context.getSharedPreferences(Constants.USER_KEY, Context.MODE_PRIVATE)
    return if (sharedPreferences.getBoolean(Constants.REGISTER_KEY, false)) {
        Home.route
    } else {
        Onboarding.route
    }
}