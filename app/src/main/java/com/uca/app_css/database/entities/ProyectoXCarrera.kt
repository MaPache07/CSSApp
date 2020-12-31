package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyectoXCarrera")
data class ProyectoXCarrera(
    @PrimaryKey
    val idProyectoCarrera: Int = -1,
    val idProyecto: Int = -1,
    val idCarrera: Int = -1,
    val idPerfil: Int = -1
)