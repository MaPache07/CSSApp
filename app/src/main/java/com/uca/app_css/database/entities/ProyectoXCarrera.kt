package com.uca.app_css.database.entities

import androidx.room.Entity

@Entity(tableName = "ProyectoXCarrera")
data class ProyectoXCarrera(
    val idProyectoCarrera: Int,
    val idProyecto: Int,
    val idCarrera: Int,
    val idPerfil: Int
)