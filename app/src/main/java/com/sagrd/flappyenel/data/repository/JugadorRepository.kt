package com.sagrd.flappyenel.data.repository

import com.sagrd.flappyenel.data.remote.api.JugadorApi
import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.util.Resources.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class JugadorRepository @Inject constructor(
    private val jugadorApi : JugadorApi
) {
    fun getJugadores() : Flow<Resource<List<JugadorDto>>> = flow{
        try{
            emit(Resource.Loading())
            val jugadores = jugadorApi.getJugadores()
            emit(Resource.Success(jugadores))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "Verificar Conexion"))
        }catch (e: HttpException)
        {
            emit(Resource.Error(e.message()?:"Error HTTP"))
        }
    }

    fun getJugadorById(id: Int): Flow<Resource<JugadorDto>> = flow {
        try {
            emit(Resource.Loading())
            val jugador = jugadorApi.getJugadorById(id)
            emit(Resource.Success(jugador))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar Conexion"))
        }
    }

    suspend fun deleteJugador(id: Int) {
        jugadorApi.deleteJugadorById(id)
    }

    suspend fun postJugador(jugador : JugadorDto) : JugadorDto?{
        return try {
            withContext(Dispatchers.IO) {
                val response = jugadorApi.postJugador(jugador)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun putJugador(jugador : JugadorDto) {
        jugadorApi.putJugador(jugador)
    }

    suspend fun login(usuario : String, clave : String) : JugadorDto?
    {
        return try {
            withContext(Dispatchers.IO) {
                val response = jugadorApi.login(usuario,clave)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun toRegister(jugador : JugadorDto): JugadorDto? {
        return try {
            withContext(Dispatchers.IO) {
                val response = jugadorApi.toRegister(jugador)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }


}