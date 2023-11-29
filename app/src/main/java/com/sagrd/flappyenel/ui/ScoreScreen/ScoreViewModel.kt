package com.sagrd.flappyenel.ui.ScoreScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.data.repository.JugadorRepository
import com.sagrd.flappyenel.util.Resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class ScoreListUiState(
    val isLoading: Boolean = false,
    val jugadores: List<JugadorDto> = emptyList(),
    val error: String = "",
)


@HiltViewModel
class ScoreViewModel @Inject constructor(
    repository: JugadorRepository
):ViewModel(){
    private val _uiState = MutableStateFlow(ScoreListUiState())
    val uiState: StateFlow<ScoreListUiState> = _uiState.asStateFlow()

    init {
        repository.getJugadores().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { it.copy(jugadores = result.data ?: emptyList()) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}