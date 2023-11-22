package com.sagrd.flappyenel.ui.nodes

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.sagrd.flappyenel.R

var alive by  mutableStateOf(true)
var chikenPosition by mutableStateOf(Offset(0f, 0f))
@Composable
fun Chiken(
    y : Float,
    modifier : Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = 20.dp, y = (y).dp)

    ) {
        Box(
            modifier = modifier
        ){
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
            Image(
                painter = painterResource(id = R.drawable.flappy__7_), contentDescription = "Player", modifier = Modifier.size(100.dp,150.dp), contentScale = ContentScale.FillBounds
                )


        }
    }
}