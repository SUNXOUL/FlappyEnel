package com.sagrd.flappyenel.data.remote.dto

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class  ServiceResponseDto<T>(
    @PrimaryKey
    @Json(name="data")
    val data : T,
    @Json(name="succes")
    val success : Boolean = true,
    @Json(name="message")
    val message : String = ""
)
@JsonClass(generateAdapter = true)
data class JugadorDto(

    @PrimaryKey
    @Json(name="jugadorId")
    val jugadorId: Int? = 0,
    @Json(name="nombreCompleto")
    val nombreCompleto: String = "none",
    @Json(name="usuario")
    val usuario: String = "none",
    @Json(name="clave")
    val clave: String = "none",
    @Json(name="puntuacion")
    val puntuacion: Int = 0
)