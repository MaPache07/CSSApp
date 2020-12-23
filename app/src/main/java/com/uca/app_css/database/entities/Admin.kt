package com.uca.app_css.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin")
data class Admin(
    @PrimaryKey
    val idAdmin: Int,
    val nombres: String,
    val apellidos: String,
    val correo: String,
    val password: String,
    val area: String,
    val estado: Boolean
)