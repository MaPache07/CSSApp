package com.uca.app_css.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CarreraWithEstudiante(
    @Embedded val carrera: Carrera,
    @Relation(
        parentColumn = "idCarrera",
        entityColumn = "idCarrera"
    )
    val estudiantes: List<Estudiante>
)