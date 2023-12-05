package com.sagrd.flappyenel.ui.RegisterScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.data.repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: JugadorRepository
) : ViewModel(){

    var claveRepetida by mutableStateOf("")
    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")

    var claveRepetidaError by mutableStateOf(false)
    var usuarioError by mutableStateOf(false)
    var claveError by mutableStateOf(false)
    var mensaje : String = ""
    fun onUsuarioChange(valor:String){
        usuario= valor
        usuarioError= valor.isNullOrBlank()
    }
    fun onClaveChange(valor:String){
        clave=valor
        claveError= valor.isNullOrBlank()
    }
    fun onClaveRepetidaChange(valor:String){
        claveRepetida= valor
        claveRepetidaError= claveRepetida != clave
    }


    fun validate() : Boolean
    {
        onClaveRepetidaChange(claveRepetida)
        onUsuarioChange(usuario)
        onClaveChange(clave)

        return !claveError && !usuarioError && !claveRepetidaError
    }

    fun toRegister(){
        if (validate()){
            viewModelScope.launch {
                repository.toRegister(JugadorDto(
                    jugadorId = 0, nombreCompleto = ""
                    , usuario = usuario, clave = clave))
                clean()
            }
        }
        else
        {
            mensaje = "INVALID DATA"
        }

    }

    fun clean()
    {
        claveRepetida = ""
        usuario = ""
        clave = ""
        mensaje=""
    }






}