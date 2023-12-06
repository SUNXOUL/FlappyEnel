package com.sagrd.flappyenel.ui.GameScreen

import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.sagrd.flappyenel.R
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.player
import com.sagrd.flappyenel.ui.nodes.Chiken
import com.sagrd.flappyenel.ui.nodes.Pipe
import com.sagrd.flappyenel.ui.nodes.alive
import com.sagrd.flappyenel.ui.nodes.chikenPosition
import com.sagrd.personas.Nav.AppScreens
import kotlin.math.absoluteValue

var points by mutableIntStateOf(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    nav :NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    var y by remember { mutableStateOf(0f) }
    val density = LocalDensity.current.density
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val max_width = displayMetrics.widthPixels / density
    var x by remember { mutableStateOf(max_width +200f) }
    val max_height = displayMetrics.heightPixels / density
    var pipe_height by remember { mutableStateOf(400f) }
    var fontPixel = Font(R.font.pixelfont).toFontFamily()
    var fast by remember { mutableStateOf(0.3f) }


    DisposableEffect(Unit) {
        alive = true
        chikenPosition = Offset(0f,0f)
        points =0

        val gravity = Thread {
            while (alive) {
                Thread.sleep(1)
                if ((y < (max_height) -10 ) && y > -150 ) {
                    y += 0.5f
                }
                else{
                    alive=false

                }
            }
            gameViewModel.onJugadorChange(JugadorDto(jugadorId = player.jugadorId, nombreCompleto = "none", usuario = player.usuario, puntuacion = points, clave = player.clave ))
            gameViewModel.lose()

        }

        val velocity = Thread {
            while (alive) {
                Thread.sleep(1)
                if (x > (max_width) - 500) {
                    x -= fast
                }
                else
                {
                    x = max_width+200
                    pipe_height = ((200..(max_height/1.1).toInt()).random()).toFloat()
                    println(pipe_height)
                    if(alive){
                        points++
                        if(fast<=1.3f){
                            fast += 0.02f
                        }
                    }
                }
            }
        }


        gravity.priority = Thread.MAX_PRIORITY
        velocity.priority = Thread.MAX_PRIORITY

        gravity.start()
        velocity.start()

        onDispose {
            gravity.interrupt()
            velocity.interrupt()
        }
    }
    Box(modifier =Modifier.fillMaxSize() ){
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        if (fast>=0.6 && fast<1f){
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.oceanbackground).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(2.5f, matchHeightConstraintsFirst = true)
            )
        }
        if (fast>=1){
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.hellbackground).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(2.5f, matchHeightConstraintsFirst = true)
            )
        }

    }

    Column (modifier = Modifier
        .fillMaxSize()
        .clickable {
            if (alive) {
                y -= 100
            }

        }){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),horizontalArrangement = Arrangement.Center) {
            Surface(color = Color(0xFFEAE4C3),modifier = Modifier.border(border = BorderStroke(width = 1.dp, color = Color.Black))) {
                Text(text = "${points} pts ",color = Color.Black, fontFamily = fontPixel, fontWeight = FontWeight.Bold , modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.h5)
            }

        }

        Box(modifier = Modifier

            .fillMaxWidth()
        ){
            Chiken(y =y)
            Pipe(x = x, y = pipe_height,
                modifier = Modifier, fast = fast
            )
            Pipe(x = x, y = (pipe_height-900),
                modifier = Modifier, fast = fast
            )

        }

    }

    if (!alive) {
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
                Image(painter = painterResource(id = R.drawable.losemodal), contentDescription ="" )
                Column(modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    Text(
                        text = "${points} pts",
                        color = Color.Black,
                        style = MaterialTheme.typography.h2,
                        fontFamily = fontPixel
                    )
                    IconButton(onClick = { nav.navigate(AppScreens.GameScreen.route) },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.retrybutton), contentDescription ="Retry")
                    }
                    IconButton(onClick = {
                        alive = true
                        nav.navigate(AppScreens.MenuScreen.route) },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.backbutton), contentDescription ="Back")
                    }

                }
            }
        }
    }
}
fun isTouching(position2: Offset, position3 : Offset): Boolean {
    return ((chikenPosition.x -position3.x).absoluteValue <20 &&  chikenPosition.y in position2.y..position3.y)
}


