package com.sagrd.flappyenel.ui.ScoreScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sagrd.flappyenel.R
import com.sagrd.personas.Nav.AppScreens

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScoreScreen(
    scoreViewModel: ScoreViewModel = hiltViewModel(),
    nav : NavController
)
{
    var players = scoreViewModel.uiState.value.jugadores
    val fontPixel = Font(R.font.pixelfont).toFontFamily()
    Column ( modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()){
        Row (modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { nav.navigate(AppScreens.MenuScreen.route) },modifier = Modifier
                .padding(10.dp)
            ) {
                Image(painter =  painterResource(id = R.drawable.backbutton), contentDescription ="Play")
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .wrapContentWidth(),
            color = Color.Transparent,
            shape = MaterialTheme.shapes.large
        ) {
            Image(
                painter = painterResource(id = R.drawable.modal),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SCOREBOARD",
                    fontFamily = fontPixel,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h3
                )
                Divider(color = Color(0xFFEF5C03), thickness = 5.dp)

                LazyColumn() {

                    items(players) { player ->
                        Card(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth()
                        ) {
                            Surface (
                                color = Color.White
                            ){
                                Row(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = player.usuario,
                                        fontFamily = fontPixel,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .weight(1f),
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(
                                        text = "${player.puntuacion.toString()} pts",
                                        fontFamily = fontPixel,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .weight(1f),
                                        style = MaterialTheme.typography.h6
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