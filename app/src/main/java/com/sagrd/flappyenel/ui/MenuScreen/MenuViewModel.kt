package com.sagrd.flappyenel.ui.MenuScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.data.repository.JugadorRepository
import com.sagrd.flappyenel.player
import com.sagrd.flappyenel.ui.GameScreen.storage.SessionStorage
import com.sagrd.flappyenel.ui.LoginScreen.JugadorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: JugadorRepository
) : ViewModel(){
    private var _uiState =  MutableStateFlow(JugadorUiState())
    var uiState : StateFlow<JugadorUiState> = _uiState.asStateFlow()
    fun login(id : Int){
        viewModelScope.launch {
            val response = repository.getJugadorById(id)

            if (response != null) {
                _uiState.update { it.copy(jugador = response.data ?: null, sesion = response.success , mensaje = response.message) }
            }
            uiState = _uiState
            _uiState.collect{jugador->
                player = jugador.jugador!!
            }

        }
    }
}