package com.sagrd.flappyenel.ui.GameScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.ui.nodes.Chiken
import com.sagrd.flappyenel.ui.nodes.Pipe
import com.sagrd.flappyenel.ui.nodes.alive
import com.sagrd.flappyenel.ui.nodes.chikenPosition
import com.sagrd.personas.Nav.AppScreens
import com.sagrd.flappyenel.R
import kotlin.math.absoluteValue

var points by mutableIntStateOf(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    nav :NavController
) {
    var y by remember { mutableStateOf(0f) }
    val density = LocalDensity.current.density
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val max_width = displayMetrics.widthPixels / density
    var x by remember { mutableStateOf(max_width +200f) }
    val max_height = displayMetrics.heightPixels / density
    var pipe_height by remember { mutableStateOf(400f) }




    DisposableEffect(Unit) {
        alive = true
        chikenPosition = Offset(0f,0f)
        points =0

        val gravity = Thread {
            while (alive) {
                Thread.sleep(1)
                if (y < (max_height) -10) {
                    y += 0.5f
                }
                else{
                    alive=false
                }
            }

        }

        val velocity = Thread {
            while (alive) {
                Thread.sleep(1)
                if (x > (max_width) - 500) {
                    x -= 0.5f
                }
                else
                {
                    x = max_width+200
                    pipe_height = ((200..(max_height/1.1).toInt()).random()).toFloat()
                    println(pipe_height)
                    if(alive){
                        points++
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
    Column (modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
            Text(text = "Puntos: ${points}")
        }

        Box(modifier = Modifier
            .clickable {
                if (alive) {
                    y -= 150
                }

            }
            .fillMaxWidth()
        ){
            Chiken(y =y ,
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .onGloballyPositioned {
                        chikenPosition = it.positionInWindow()
                    }
            )
            Pipe(x = x, y = pipe_height,
                modifier = Modifier, nav = nav
            )
            Pipe(x = x, y = (pipe_height-850),
                modifier = Modifier, nav = nav
            )

        }

    }

    if (!alive) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
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
                Column(modifier = Modifier.padding(top =100.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "${points} pts", style = MaterialTheme.typography.h2, fontFamily = FontFamily.)
                }
            }
        }
    }
}
fun isTouching(position2: Offset, position3 : Offset): Boolean {
    return ((chikenPosition.x -position3.x).absoluteValue <20 &&  chikenPosition.y in position2.y..position3.y)
}
