package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "proyecto")
data class Proyecto(
    @PrimaryKey
    val idProyecto: Int,
    val estado: Boolean,
    val contraparte: String,
    val cupos: Int,
    val descripcion: String,
    val encargado: String,
    val fecha_inicio: String,
    val fecha_fin: String,
    val horario: String,
    val nombre: String,
    val tipo_horas: String,
    val modifiedAt: String,
    val modifiedBy: String,
    val createdAt: String
)