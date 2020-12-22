package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProyectoXCarrera")
data class ProyectoXCarrera(
    @PrimaryKey
    val idProyectoCarrera: Int,
    val idProyecto: Int,
    val idCarrera: Int,
    val idPerfil: Int
)