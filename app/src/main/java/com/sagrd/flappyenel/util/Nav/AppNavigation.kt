package com.sagrd.personas.Nav

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagrd.flappyenel.ui.GameScreen.GameScreen
import com.sagrd.flappyenel.ui.MenuScreen.MenuScreen
import com.sagrd.flappyenel.ui.SplashScreen.SplashScreen


@Composable
fun AppNavigation(
    context: Context,
    music : MediaPlayer
)
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        //Home Screen
        composable(AppScreens.SplashScreen.route) {
           SplashScreen(nav = navController)
        }
        composable(AppScreens.MenuScreen.route) {
            MenuScreen(music =  music , nav = navController)
        }
        composable(AppScreens.GameScreen.route) {
            GameScreen(nav= navController)
        }

    }
}


