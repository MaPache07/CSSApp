package com.uca.app_css.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class FacultadXCarrera(
    @Embedded val facultad: Facultad,
    @Relation(
        parentColumn = "idFacultad",
        entityColumn = "idFacultad"
    )
    val carreras: List<Carrera>
)