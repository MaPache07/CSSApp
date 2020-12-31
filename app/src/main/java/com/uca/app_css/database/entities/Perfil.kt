package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfil")
data class Perfil(
    @PrimaryKey
    val idPerfil: Int = -1,
    val perfil: Int = 0,
    val descripcion: String = ""
)