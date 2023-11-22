package com.sagrd.flappyenel.ui.MenuScreen

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.personas.Nav.AppScreens
import kotlinx.coroutines.delay

@Composable
fun MenuScreen(
    music : MediaPlayer,
    nav : NavController
){

    var musicOn = painterResource(id = R.drawable.soundbutton)
    var musicOff = painterResource(id = R.drawable.soundoffbutton)
    var musicButtonPaint : Painter by remember {
        mutableStateOf(musicOn)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Row (modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {
               if(music.isPlaying){
                   music.pause()
                   musicButtonPaint = musicOff
               }
                else{
                   music.start()
                   musicButtonPaint = musicOn
               }
            },
                modifier=Modifier.size(50.dp)
            ){
                Image(painter =  musicButtonPaint, contentDescription ="Music")
            }

        }
        Box {
            Box{
                val scale = remember { Animatable(1f) }

                LaunchedEffect(key1 = true) {
                    while (true) {
                        scale.animateTo(if (scale.value > 1.2f) 1f else 1.3f)
                        delay(500)
                    }
                }

                Image(painter = painterResource(id = R.drawable.flappyenellogo), contentDescription = "Logo", modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(scale.value, matchHeightConstraintsFirst = true))
            }

            Row (modifier = Modifier.padding(top=350.dp)){
                Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()){
                    IconButton(onClick = { nav.navigate(route = AppScreens.GameScreen.route) },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.playbutton), contentDescription ="Play", modifier = Modifier
                            .clip(RectangleShape),contentScale = ContentScale.FillWidth)
                    }
                    IconButton(onClick = { /*TODO*/ },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.scorebutton), contentDescription ="Play")
                    }
                    IconButton(onClick = { /*TODO*/ },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.storebutton), contentDescription ="Play")
                    }
                    IconButton(onClick = { /*TODO*/ },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.creditsbutton), contentDescription ="Play")
                    }
                }
            }
        }

    }
}