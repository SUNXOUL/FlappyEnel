package com.sagrd.flappyenel.ui.RegisterScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sagrd.flappyenel.R
import com.sagrd.flappyenel.toLogedUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterModal(
    registerViewModel: RegisterViewModel
) {

    val fontPixel = Font(R.font.pixelfont).toFontFamily()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
                Image(painter = painterResource(id = R.drawable.modal), contentDescription ="" ,contentScale = ContentScale.FillBounds, modifier = Modifier.size(400.dp,400.dp))
                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
                {
                    Text(text = "Register", fontFamily = fontPixel, fontWeight = FontWeight.Bold , modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    TextField(value = registerViewModel.usuario, onValueChange = {registerViewModel.onUsuarioChange(it)}, label = {
                        Text(text = "USERNAME", fontFamily = fontPixel)
                    },
                        isError = registerViewModel.usuarioError,
                        maxLines = 1,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFEF5C03),
                            cursorColor =  Color(0xFFEF5C03),
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    TextField(
                        value = registerViewModel.clave,
                        onValueChange = { registerViewModel.onClaveChange(it)},
                        label = { Text(text="PASSWORD", fontFamily = fontPixel) },
                        singleLine = true,
                        isError = registerViewModel.claveError,
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
                    TextField(
                        value = registerViewModel.claveRepetida,
                        onValueChange = { registerViewModel.onClaveRepetidaChange(it)},
                        label = { Text(text="REPEAT THE PASSWORD", fontFamily = fontPixel) },
                        singleLine = true,
                        isError = registerViewModel.claveRepetidaError,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFEF5C03),
                            cursorColor =  Color(0xFFEF5C03),
                            unfocusedBorderColor = Color.Gray
                        )
                    )
                    Text(text = registerViewModel.mensaje, fontFamily = fontPixel)
                    Spacer(modifier = Modifier.padding(top=10.dp))
                    IconButton(onClick = { registerViewModel.toRegister() },modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Image(painter =  painterResource(id = R.drawable.loginbutton), contentDescription ="login")
                    }
                    TextButton(onClick = { toLogedUser=true }) {
                        Text(text = "I have Account", fontFamily = fontPixel)
                    }

                }
            }
        }

}