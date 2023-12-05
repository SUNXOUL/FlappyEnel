package com.sagrd.flappyenel.ui.GameScreen

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
class GameViewModel @Inject constructor(
    private val repository: JugadorRepository
) : ViewModel(){

    var jugador by mutableStateOf(JugadorDto(
        jugadorId = 0, nombreCompleto = ""
    , usuario = "", clave = ""))

    var jugadorError by mutableStateOf(false)

    fun onJugadorChange(value : JugadorDto)
    {
        jugador = value
        jugadorError = value.jugadorId == 0 || value.usuario.isNullOrBlank()
    }

    fun lose()
    {
        viewModelScope.launch {
            repository.putJugador(jugador)
        }
    }
}