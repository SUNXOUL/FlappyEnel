package com.sagrd.flappyenel.data.remote.dto

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class  ServiceResponseDto(
    @PrimaryKey
    @Json(name="data")
    val data : JugadorDto,
    @Json(name="succes")
    val success : Boolean,
    @Json(name="message")
    val message : String
)
@JsonClass(generateAdapter = true)
data class JugadorDto(

    @PrimaryKey
    @Json(name="jugadorId")
    val jugadorId : Int?,
    @Json(name="nombreCompleto")
    val nombreCompleto : String,
    @Json(name="clave")
    val clave : String,
    @Json(name="puntuacion")
    val puntuacion : Int = 0
)