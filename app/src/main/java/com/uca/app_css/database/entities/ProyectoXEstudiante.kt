package com.uca.app_css.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyectoXEstudiante")
data class ProyectoXEstudiante(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProyectoEstudiante")
    val idProyectoXEstudiante: Int = -1,
    val idProyecto: Int = -1,
    val idEstudiante: Int = -1,
    val appliedAt: String= "",
    val estado: Int= -1,
    val modifiedAt: String = "",
    val modifiedBy: String = ""
)