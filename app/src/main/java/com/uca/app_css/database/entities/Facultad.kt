package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facultad")
data class Facultad(
    @PrimaryKey
    val idFacultad: Int,
    val nombre: String
)