package com.sagrd.flappyenel.ui.StoreScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.flappyenel.skinSelected
import com.sagrd.flappyenel.skins
import com.sagrd.personas.Nav.AppScreens

@Composable
fun StoreScreen(
    nav : NavController
){
    val fontPixel = Font(R.font.pixelfont).toFontFamily()
    Column(Modifier.padding(10.dp)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)){
            IconButton(onClick = { nav.navigate(AppScreens.MenuScreen.route) },modifier = Modifier
                .padding(10.dp)
            ) {
                Image(painter =  painterResource(id = R.drawable.backbutton), contentDescription ="Play")
            }
        }
        Text(
            text = "SKINS",
            fontFamily = fontPixel,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.h3
        )
        Divider(color = Color(0xFFEF5C03), thickness = 5.dp)
        Box(){
            Image(
                painter = painterResource(id = R.drawable.modal),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize()
            )
            LazyRow(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                items(skins){ skin ->
                    Surface(
                        modifier = Modifier.padding(10.dp),
                        color = Color.Transparent
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = skin),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1.5f)
                            )
                            TextButton(onClick = { skinSelected = skins.indexOf(skin) }, modifier = Modifier.weight(1f)) {
                                if(skinSelected == skins.indexOf(skin)){
                                    Text(
                                        text = "SELECTED",
                                        fontFamily = fontPixel,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFEF5C03),
                                        modifier = Modifier.padding(5.dp),
                                        style = MaterialTheme.typography.h4
                                    )
                                }
                                else{
                                    Text(
                                        text = "SELECT",
                                        fontFamily = fontPixel,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(5.dp),
                                        style = MaterialTheme.typography.h4
                                    )
                                }

                            }
                        }

                    }

                }
            }
        }

    }
}