package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyectoXEstudiante")
data class ProyectoXEstudiante(
    @PrimaryKey
    val idProyectoXEstudiante: Int,
    val idProyecto: Int,
    val idEstudiante: Int,
    val appliedAt: String,
    val estado: Boolean,
    val modifiedAt: String,
    val modifiedBy: String
)