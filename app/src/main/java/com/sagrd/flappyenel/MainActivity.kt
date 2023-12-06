package com.sagrd.flappyenel

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.ui.GameScreen.storage.SessionStorage
import com.sagrd.flappyenel.ui.MenuScreen.MenuScreen
import com.sagrd.flappyenel.ui.SplashScreen.SplashScreen
import com.sagrd.flappyenel.ui.theme.FlappyEnelTheme
import com.sagrd.personas.Nav.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlappyEnelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var music = MediaPlayer.create(this, R.raw.theme)
                    Background()
                    DisposableEffect(Unit){
                        music.start()
                        music.isLooping = true

                        onDispose {
                            music.stop()
                            music.release()
                            music = null
                        }
                    }
                    AppNavigation(this,music)
                }
            }
        }
    }
}

var toLogedUser by mutableStateOf(true)
var skinSelected by mutableIntStateOf(0)
var skins = mutableListOf<Int>((R.drawable.flappy__7_),(R.drawable.miguelskin),(R.drawable.gregoryskin),(R.drawable.juniorskin) )
var player by mutableStateOf(JugadorDto(0,"","",""))
@Composable
fun Background(){
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
    )
}