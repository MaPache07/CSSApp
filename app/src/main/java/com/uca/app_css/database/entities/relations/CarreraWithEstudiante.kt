package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Estudiante

data class CarreraWithEstudiante(
        @Embedded val carrera: Carrera,
        @Relation(
        parentColumn = "idCarrera",
        entityColumn = "idCarrera"
    )
    val estudiantes: List<Estudiante>
)