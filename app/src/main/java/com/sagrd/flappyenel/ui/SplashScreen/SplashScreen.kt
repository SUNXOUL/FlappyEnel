package com.sagrd.flappyenel.ui.SplashScreen

import android.content.Context
import android.media.MediaPlayer
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.personas.Nav.AppScreens

@Composable
fun SplashScreen(
    nav : NavController
){
    var image = painterResource(id = R.drawable.creditsbutton)
    var image2 =  painterResource(id = R.drawable.storebutton)
    var image3 =  painterResource(id = R.drawable.storebutton)
    DisposableEffect(Unit){
        Thread.sleep(4000)
        nav.navigate(route = AppScreens.MenuScreen.route)
        onDispose {

        }
    }

}