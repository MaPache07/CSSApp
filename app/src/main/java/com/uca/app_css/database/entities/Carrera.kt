package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrera")
data class Carrera(
    @PrimaryKey
    val idCarrera: Int = -1,
    val idFacultad: Int = -1,
    val nombre: String = ""
)