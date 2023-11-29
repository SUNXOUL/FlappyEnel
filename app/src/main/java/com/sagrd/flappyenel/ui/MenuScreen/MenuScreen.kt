package com.sagrd.flappyenel.ui.MenuScreen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.flappyenel.ui.nodes.alive
import com.sagrd.personas.Nav.AppScreens
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    music : MediaPlayer,
    nav : NavController
){

    val musicOn = painterResource(id = R.drawable.soundbutton)
    val musicOff = painterResource(id = R.drawable.soundoffbutton)
    val fontPixel = Font(R.font.pixelfont).toFontFamily()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var musicButtonPaint : Painter by remember {
        mutableStateOf(musicOn)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Row (modifier = Modifier.fillMaxWidth()){
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

                }
            }
        }

    }
    if (alive) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
            }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                color = Color.Transparent,
                shape = MaterialTheme.shapes.large
            ) {
                Image(painter = painterResource(id = R.drawable.modal), contentDescription ="" )
                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
                {
                    Text(text = "Login", fontFamily = fontPixel, fontWeight = FontWeight.Bold , modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    TextField(value = "", onValueChange = {}, placeholder = {
                        Text(text = "USERNAME", fontFamily = fontPixel)
                    },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFEF5C03),
                            cursorColor =  Color(0xFFEF5C03),
                            unfocusedBorderColor = Color.Gray
                        )
                        )
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    TextField(
                        value = "sdasdsadsdasd",
                        onValueChange = { },
                        label = { Text(text="PASSWORD", fontFamily = fontPixel) },
                        singleLine = true,
                        placeholder = { Text("Password") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
                            val description = if (passwordVisible) "Hide password" else "Show password"
                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = image, description)
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFEF5C03),
                            cursorColor =  Color(0xFFEF5C03),
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    IconButton(onClick = { /*TODO*/ },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.loginbutton), contentDescription ="login")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Create Account", fontFamily = fontPixel)
                    }

                }


            }
        }
    }
}


