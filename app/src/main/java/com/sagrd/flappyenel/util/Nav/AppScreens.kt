package com.sagrd.personas.Nav

sealed class AppScreens(val route : String)
{
    object SplashScreen: AppScreens("splash_Screen")
    object MenuScreen: AppScreens("menu_Screen")
    object GameScreen: AppScreens("game_Screen")
    object LoseScreen: AppScreens("lose_Screen")
    object ScoreScreen: AppScreens("Score_Screen")
    object StoreScreen: AppScreens("store_Screen")


}
