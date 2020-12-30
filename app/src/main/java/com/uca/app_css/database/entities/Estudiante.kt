package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante")
data class Estudiante(
    @PrimaryKey
    val idEstudiante: Int = -1,
    val nombres: String = "",
    val apellidos: String = "",
    val carnet: String = "",
    val correo: String = "",
    val estado: Boolean = false,
    val genero: String = "",
    val default_password: String = "",
    val password: String = "",
    val modifiedAt: String = "",
    val idPerfil: Int = -1,
    val idCarrera: Int = -1
)