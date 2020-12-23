package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrera")
data class Carrera(
    @PrimaryKey
    val idCarrera: Int,
    val idFacultad: Int,
    val nombre: String
)