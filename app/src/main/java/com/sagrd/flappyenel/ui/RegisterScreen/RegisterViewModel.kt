package com.sagrd.flappyenel.ui.RegisterScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sagrd.flappyenel.data.repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject




@HiltViewModel
class RegisterViewModel @Inject constructor(
    repository: JugadorRepository
) : ViewModel(){

    var nombreCompleto by mutableStateOf("")
    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")

    var nombreCompletoError by mutableStateOf(false)
    var usuarioError by mutableStateOf(false)
    var claveError by mutableStateOf(false)

    fun onUsuarioChange(valor:String){
        usuario= valor
        usuarioError= valor.isNullOrBlank()
    }
    fun onClaveChange(valor:String){
        clave=valor
        claveError= valor.isNullOrBlank()
    }
    fun onNombreCompletoChange(valor:String){
        nombreCompleto= valor
        nombreCompletoError= valor.isNullOrBlank()
    }



}