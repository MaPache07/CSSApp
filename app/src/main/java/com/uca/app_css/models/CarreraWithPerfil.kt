package com.uca.app_css.models

import androidx.room.Entity

@Entity
data class CarreraWithPerfil(
    val idCarrera: Int,
    val idPerfil: Int,
    val carrera: String,
    val perfil: Int,
    val perfilDesc: String
)
