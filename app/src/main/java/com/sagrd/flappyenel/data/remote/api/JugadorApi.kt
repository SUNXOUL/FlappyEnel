package com.sagrd.flappyenel.data.remote.api

import com.sagrd.flappyenel.data.remote.dto.JugadorDto
import com.sagrd.flappyenel.data.remote.dto.ServiceResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JugadorApi {

    @GET("api/Jugador")
    suspend fun getJugadores() : ServiceResponseDto<List<JugadorDto>>?
    @GET("api/Jugador/{id}")
    suspend fun getJugadorById(@Path("id") jugadorId : Int) : ServiceResponseDto<JugadorDto>?
    @POST("api/Jugador")
    suspend fun postJugador(@Body jugador : JugadorDto) : Response<JugadorDto>
    @DELETE("api/Jugador/{id}")
    suspend fun deleteJugadorById(@Path("id") jugadorId : Int) : Response<Unit>
    @PUT("api/Jugador")
    suspend fun putJugador(@Body jugador : JugadorDto) : Response<Unit>
    @GET("api/Jugador/{usuario}/{clave}")
    suspend fun login(@Path("usuario") usuario : String, @Path("clave") clave : String) :  Response<ServiceResponseDto<JugadorDto>>
    @POST("api/Jugador/ToRegister")
    suspend fun toRegister(@Body jugador : JugadorDto) : Response<ServiceResponseDto<JugadorDto>>
}