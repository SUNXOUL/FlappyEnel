package com.sagrd.flappyenel.ui.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.data.remote.dto.ServiceResponseDto
import com.sagrd.flappyenel.data.repository.JugadorRepository
import com.sagrd.flappyenel.util.Resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class JugadorUiState(
    var sesion : Boolean = false,
    var jugador : JugadorDto? = JugadorDto(0,"","",""),
    var mensaje : String = ""
)


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository : JugadorRepository
) : ViewModel(){

    private val _uiState =  MutableStateFlow(JugadorUiState())
    val uiState : StateFlow<JugadorUiState> = _uiState.asStateFlow()

    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")


    var onUsuarioError by mutableStateOf(false)
    var onClaveError by mutableStateOf(false)

    fun onUsuarioChange(valor:String){
        usuario= valor
        onUsuarioError= valor.isNullOrBlank()
    }
    fun onClaveChange(valor:String){
        clave = valor
        onClaveError= valor.isNullOrBlank()
    }

    fun login()
    {
        viewModelScope.launch {
            var response = repository.login(usuario, clave)

            if (response != null) {
                _uiState.update { it.copy(jugador = response.data ?: null, sesion = response.success , mensaje = response.message) }
            }
            else
            {
                _uiState.value.mensaje = "Error para iniciar sesion"
                _uiState.value.sesion = false
            }

        }

    }

    fun clean()
    {
        usuario = ""
        clave = ""
    }

    fun validate() : Boolean{
        onUsuarioChange(usuario)
        onClaveChange(clave)
        return onUsuarioError || onClaveError

    }





}