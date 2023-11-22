package com.sagrd.flappyenel.data.remote.api

import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JugadorApi {

    @GET("api/Jugador")
    suspend fun getJugadores() : List<JugadorDto>
    @GET("api/Jugador/{id}")
    suspend fun getJugadorById(@Path("id") jugadorId : Int) : JugadorDto
    @POST("api/Jugador")
    suspend fun postJugador(@Body jugador : JugadorDto) : Response<JugadorDto>
    @DELETE("api/Jugador/{id}")
    suspend fun deleteJugadorById(@Path("id") jugadorId : Int) : Response<Unit>

}