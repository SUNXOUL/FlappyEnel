package com.sagrd.flappyenel.ui.LoseScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sagrd.flappyenel.ui.GameScreen.points
import com.sagrd.personas.Nav.AppScreens

@Composable
fun LoseScreen(
    nav : NavController
){
Surface(
    color = MaterialTheme.colors.error,
    modifier =  Modifier.fillMaxSize()
) {
    Column(modifier = Modifier.padding(30.dp)) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "SCORE: ${points}")
        }
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Button(onClick = { nav.navigate(AppScreens.GameScreen.route) }) {
                Text(text = "RETRY")
            }
            Button(onClick = { nav.navigate(AppScreens.MenuScreen.route) }) {
                Text(text = "HOME")
            }
        }
        
    }
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoseDialog(){

}