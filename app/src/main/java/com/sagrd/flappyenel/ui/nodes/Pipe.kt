package com.sagrd.flappyenel.ui.nodes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import kotlin.math.absoluteValue

@SuppressLint("UnrememberedMutableState")
@Composable
fun Pipe(
    x : Float,
    y : Float,
    modifier : Modifier,
    fast : Float,
    ) {
    var obstaculePosition : Offset by remember {
        mutableStateOf(Offset(x=0f,y=0f))
    }
    var obstaculeCoverPosition : Offset by remember {
        mutableStateOf(Offset(x=0f,y=0f))
    }

    DisposableEffect(Unit) {

        val collisions = Thread {
            Thread.sleep(200)
            while (alive) {
                if(isTouching(obstaculeCoverPosition,obstaculePosition)){
                    alive=false
                }
            }
        }
        collisions.priority = Thread.MAX_PRIORITY
        collisions.start()
        onDispose {
            collisions.interrupt()
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .offset(x = x.dp, y = y.dp)

        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            obstaculeCoverPosition = it.positionInWindow()
                        }
                        .size(100.dp, 50.dp)

                ){
                    Image(painter = painterResource(id = R.drawable.pipe_cover), contentDescription ="",
                        modifier= Modifier.fillMaxSize(), colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                            getTheme(fast)
                        )) )
                }
                Box(
                    modifier = modifier
                ){
                    Image(painter = painterResource(id = R.drawable.pipe_pillar), contentDescription ="", modifier= Modifier
                        .clip(RectangleShape)
                        .size(75.dp, 600.dp),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix(getTheme(fast)))
                    )
                }
                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            obstaculePosition = it.positionInWindow()
                        }
                        .size(100.dp, 50.dp)

                ){
                    Image(painter = painterResource(id = R.drawable.pipe_cover), contentDescription ="",
                        modifier= Modifier.fillMaxSize(),
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix(getTheme(fast))))
                }
            }
        }
    }
}
fun isTouching(position2: Offset, position3 : Offset): Boolean {
    return ((chikenPosition.x -position3.x).absoluteValue <40 &&  chikenPosition.y in position2.y-50..position3.y+50)
}
fun getTheme(fast:Float) : FloatArray{
    val hielo = floatArrayOf(
        1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 5.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )
    val lava = floatArrayOf(
        4.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )
    val verde = floatArrayOf(
        -1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )
    if (fast>=1 && fast<1.7f) {
        return hielo
    }
    if (fast>=1.7) {
        return lava
    }
    return verde
}