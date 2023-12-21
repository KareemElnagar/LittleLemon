package com.kareem.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kareem.littlelemon.util.Constants
import com.kareem.littlelemon.util.Home
import com.kareem.littlelemon.util.Onboarding
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