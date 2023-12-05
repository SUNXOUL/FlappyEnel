package com.sagrd.personas.Nav

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagrd.flappyenel.ui.GameScreen.GameScreen
import com.sagrd.flappyenel.ui.LoseScreen.LoseScreen
import com.sagrd.flappyenel.ui.MenuScreen.MenuScreen
import com.sagrd.flappyenel.ui.ScoreScreen.ScoreScreen
import com.sagrd.flappyenel.ui.SplashScreen.SplashScreen
import com.sagrd.flappyenel.ui.StoreScreen.StoreScreen


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
            MenuScreen(music =  music , nav = navController )
        }
        composable(AppScreens.GameScreen.route) {
            GameScreen(nav= navController)
        }
        composable(AppScreens.LoseScreen.route) {
            LoseScreen(nav= navController)
        }
        composable(AppScreens.ScoreScreen.route) {
            ScoreScreen(nav= navController)
        }
        composable(AppScreens.StoreScreen.route) {
            StoreScreen(nav= navController)
        }

    }
}


