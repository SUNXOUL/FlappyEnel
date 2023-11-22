package com.sagrd.flappyenel.ui.nodes

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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.personas.Nav.AppScreens
import kotlin.math.absoluteValue

@Composable
fun Pipe(
    x : Float,
    y : Float,
    modifier : Modifier,
    nav : NavController
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
                    Image(painter = painterResource(id = R.drawable.pipe_cover), contentDescription ="", modifier= Modifier.fillMaxSize() )
                }
                Box(
                    modifier = modifier
                ){
                    Image(painter = painterResource(id = R.drawable.pipe_pillar), contentDescription ="", modifier= Modifier
                        .clip(RectangleShape)
                        .size(75.dp, 600.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            obstaculePosition = it.positionInWindow()
                        }
                        .size(100.dp, 50.dp)

                ){
                    Image(painter = painterResource(id = R.drawable.pipe_cover), contentDescription ="", modifier= Modifier.fillMaxSize() )
                }
            }
        }
    }
}
fun isTouching(position2: Offset, position3 : Offset): Boolean {
    return ((chikenPosition.x -position3.x).absoluteValue <20 &&  chikenPosition.y in position2.y..position3.y)
}