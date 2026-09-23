package com.kareem.littlelemon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.kareem.littlelemon.ui.components.BottomNavigation
import com.kareem.littlelemon.ui.theme.LittleLemonTheme
import com.kareem.littlelemon.util.Onboarding
import com.kareem.littlelemon.util.Splash
import com.kareem.littlelemon.util.askForPermission
import com.kareem.littlelemon.util.createNotificationChannel
import com.kareem.littlelemon.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(context = applicationContext)
        askForPermission(applicationContext,this)
        setContent {
            LittleLemonTheme(darkTheme = false) {
                val navController = rememberAnimatedNavController()
                val sharedMenuViewModel: MenuViewModel = viewModel()
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            val showBottomBar = currentRoute != null &&
                                    currentRoute != Onboarding.route &&
                                    currentRoute != Splash.route

                            AnimatedVisibility(
                                visible = showBottomBar,
                                enter = fadeIn(animationSpec = tween(300)),
                                exit = fadeOut(animationSpec = tween(300))
                            ) {
                                BottomNavigation(navController = navController, sharedMenuViewModel = sharedMenuViewModel)
                            }
                        }
                    ) {
                        Column(Modifier.padding(it)) {
                            NavigationComposable(
                                context = applicationContext,
                                navController = navController,
                                sharedMenuViewModel = sharedMenuViewModel
                            )
                        }
                    }


                }
            }
        }
    }
}

