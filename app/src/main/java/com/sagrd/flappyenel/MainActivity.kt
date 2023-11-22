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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sagrd.flappyenel.ui.MenuScreen.MenuScreen
import com.sagrd.flappyenel.ui.SplashScreen.SplashScreen
import com.sagrd.flappyenel.ui.theme.FlappyEnelTheme
import com.sagrd.personas.Nav.AppNavigation

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