package com.kareem.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kareem.littlelemon.ui.screens.DishDetails
import com.kareem.littlelemon.ui.screens.Home
import com.kareem.littlelemon.ui.screens.MenuScreen
import com.kareem.littlelemon.ui.screens.Onboarding
import com.kareem.littlelemon.ui.screens.Orders
import com.kareem.littlelemon.ui.screens.Profile
import com.kareem.littlelemon.util.AppConstants
import com.kareem.littlelemon.util.DishDetails
import com.kareem.littlelemon.util.Home
import com.kareem.littlelemon.util.MenuScreen
import com.kareem.littlelemon.util.Onboarding
import com.kareem.littlelemon.util.Orders
import com.kareem.littlelemon.util.Profile
import com.kareem.littlelemon.viewmodel.MenuViewModel

@Composable
fun NavigationComposable(context: Context, navController: NavHostController, sharedMenuViewModel: MenuViewModel) {
    NavHost(navController = navController,
        startDestination = determineDestination(context)
    ) {
        composable(Home.route){
            Home(navController, sharedMenuViewModel)
        }
        composable(Profile.route){
            Profile(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(MenuScreen.route){
            MenuScreen(navController, sharedMenuViewModel)
        }
        composable(Orders.route){
            Orders(navController, sharedMenuViewModel)
        }
        composable("${DishDetails.route}/{dishId}") { backStackEntry ->
            val dishId = backStackEntry.arguments?.getString("dishId")?.toIntOrNull()
            DishDetails(navController, dishId, sharedMenuViewModel)
        }
    }
}

fun determineDestination(context: Context): String {
    val sharedPreferences = context.getSharedPreferences(AppConstants.SharedPrefs.USER_KEY, Context.MODE_PRIVATE)
    return if (sharedPreferences.getBoolean(AppConstants.SharedPrefs.REGISTER_KEY, false)) {
        Home.route
    } else {
        Onboarding.route
    }
}