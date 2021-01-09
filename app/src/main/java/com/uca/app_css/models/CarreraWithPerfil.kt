package com.uca.app_css.models

import androidx.room.Entity

//Modelo que contiene los datos del perfil para cada carrera de un determinado proyecto
@Entity
data class CarreraWithPerfil(
    val idCarrera: Int,
    val idPerfil: Int,
    val carrera: String,
    val perfil: Int,
    val perfilDesc: String
)
