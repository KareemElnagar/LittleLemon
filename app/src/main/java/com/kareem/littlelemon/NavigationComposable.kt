package com.kareem.littlelemon

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kareem.littlelemon.ui.screens.DishDetails
import com.kareem.littlelemon.ui.screens.Home
import com.kareem.littlelemon.ui.screens.MenuScreen
import com.kareem.littlelemon.ui.screens.Onboarding
import com.kareem.littlelemon.ui.screens.Orders
import com.kareem.littlelemon.ui.screens.Profile
import com.kareem.littlelemon.ui.splash.LemonSplashScreen
import com.kareem.littlelemon.util.AppConstants
import com.kareem.littlelemon.util.Cart
import com.kareem.littlelemon.util.DishDetails
import com.kareem.littlelemon.util.Home
import com.kareem.littlelemon.util.MenuScreen
import com.kareem.littlelemon.util.Onboarding
import com.kareem.littlelemon.util.Profile
import com.kareem.littlelemon.util.Splash
import com.kareem.littlelemon.viewmodel.MenuViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComposable(
    context: Context,
    navController: NavHostController,
    sharedMenuViewModel: MenuViewModel
) {
    AnimatedNavHost(
        navController = navController,
//        startDestination = determineDestination(context)
        startDestination = Splash.route
    ) {

        composable(
            Splash.route,
            exitTransition = { fadeOut(animationSpec = tween(400)) }

        ) {
            val sharedPreferences = context.getSharedPreferences(
                AppConstants.SharedPrefs.USER_KEY, Context.MODE_PRIVATE
            )
            val isRegistered = sharedPreferences.getBoolean(
                AppConstants.SharedPrefs.REGISTER_KEY, false
            )
            val destination = if (isRegistered) Home.route else Onboarding.route

            LemonSplashScreen(
                onSplashComplete = {
                    navController.navigate(destination) {
                        popUpTo(Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            Home.route,
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }) {
            Home(navController, sharedMenuViewModel)
        }
        composable(
            Profile.route,
            enterTransition = { fadeIn(animationSpec = tween(300)) }

        ) {
            Profile(navController)
        }
        composable(
            Onboarding.route,
            enterTransition = { fadeIn(animationSpec = tween(300)) }
        ) {
            Onboarding(navController)
        }
        composable(
            MenuScreen.route,
            enterTransition = { fadeIn(animationSpec = tween(300)) }
        ) {
            MenuScreen(navController, sharedMenuViewModel)
        }
        composable(Cart.route, enterTransition = { fadeIn(animationSpec = tween(300)) }
        ) {
            Orders(navController, sharedMenuViewModel)
        }
        composable(
            "${DishDetails.route}/{dishId}",
            enterTransition = { fadeIn(animationSpec = tween(300)) }
        ) { backStackEntry ->
            val dishId = backStackEntry.arguments?.getString("dishId")?.toIntOrNull()
            DishDetails(navController, dishId, sharedMenuViewModel)
        }
    }
}

fun determineDestination(context: Context): String {
    val sharedPreferences =
        context.getSharedPreferences(AppConstants.SharedPrefs.USER_KEY, Context.MODE_PRIVATE)
    return if (sharedPreferences.getBoolean(AppConstants.SharedPrefs.REGISTER_KEY, false)) {
        Home.route
    } else {
        Onboarding.route
    }
}
