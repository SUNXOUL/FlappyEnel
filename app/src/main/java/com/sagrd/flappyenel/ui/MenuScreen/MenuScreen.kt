package com.sagrd.flappyenel.ui.MenuScreen

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.player
import com.sagrd.flappyenel.toLogedUser
import com.sagrd.flappyenel.ui.GameScreen.storage.SessionStorage
import com.sagrd.flappyenel.ui.LoginScreen.LoginModal
import com.sagrd.flappyenel.ui.LoginScreen.LoginViewModel
import com.sagrd.flappyenel.ui.RegisterScreen.RegisterModal
import com.sagrd.flappyenel.ui.RegisterScreen.RegisterViewModel
import com.sagrd.personas.Nav.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var exit by mutableStateOf(false)
@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    music : MediaPlayer,
    nav : NavController,
    storage : SessionStorage,
    loginViewModel : LoginViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
    menuViewModel: MenuViewModel = hiltViewModel()
){
    val fontPixel = Font(R.font.pixelfont).toFontFamily()
    val musicOn = painterResource(id = R.drawable.soundbutton)
    val musicOff = painterResource(id = R.drawable.soundoffbutton)
    var musicButtonPaint : Painter by remember {
        mutableStateOf(musicOn)
    }
    if(exit){
        ExitModal(storage)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
        {
            IconButton(onClick = {
                musicButtonPaint = if(music.isPlaying){
                    music.pause()
                    musicOff
                } else{
                    music.start()
                    musicOn
                }
            },
                modifier=Modifier.size(50.dp)
            ){
                Image(painter =  musicButtonPaint, contentDescription ="Music")
            }

            Text(text = player.usuario, fontFamily = fontPixel, fontWeight = FontWeight.Bold , modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.h4)
                if(player.jugadorId != 0)
                {
                    IconButton(onClick = { exit = true },modifier = Modifier
                        .padding(start = 35.dp)
                        .fillMaxWidth()
                        .size(50.dp)) {
                        Image(painter =  painterResource(id = R.drawable.exitbutton), contentDescription ="Play")
                    }
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
                    IconButton(onClick = { nav.navigate(AppScreens.ScoreScreen.route) },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.scorebutton), contentDescription ="Play")
                    }
                    IconButton(onClick = { nav.navigate(AppScreens.StoreScreen.route) },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.storebutton), contentDescription ="Play")
                    }

                }
            }
        }
        if (storage.getID.collectAsState(initial = 0).value ==0 || storage.getID.collectAsState(initial = 0).value == null  ){
            if (toLogedUser){
                LoginModal(loginViewModel = loginViewModel, storage = storage)
            }
            else {
                RegisterModal( registerViewModel = registerViewModel, storage = storage)
            }
        }
        else
        {
            println(storage.getID.collectAsState(initial = 0).value)
            storage.getID.collectAsState(initial = 0).value?.let { menuViewModel.login(it) }
        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitModal(
    storage : SessionStorage
){
    val coroutineScope = rememberCoroutineScope()
    val fontPixel = Font(R.font.pixelfont).toFontFamily()

    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .size(250.dp),
            color = Color.Transparent,
            shape = MaterialTheme.shapes.large
        ) {
            Image(painter = painterResource(id = R.drawable.modal), contentDescription ="", contentScale = ContentScale.FillBounds )
            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
            {
                Text(text = "DO YOU WANT TO LOG OUT?", fontFamily = fontPixel, fontWeight = FontWeight.Bold , modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.padding(top=10.dp))
                Row {
                    TextButton(onClick = {
                        coroutineScope.launch {
                            storage.saveID(0)
                            player = JugadorDto(0,"","","",0)
                        }
                    }) {
                        Text(text = "SI", fontFamily = fontPixel,style = MaterialTheme.typography.h5, color = Color.Red)
                    }
                    TextButton(onClick = { exit = false }) {
                        Text(text = "NO", fontFamily = fontPixel,style = MaterialTheme.typography.h5)
                    }
                }

            }
        }
    }
}

