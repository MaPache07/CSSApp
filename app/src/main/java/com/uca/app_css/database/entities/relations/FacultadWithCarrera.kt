package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Facultad

data class FacultadWithCarrera(
        @Embedded val facultad: Facultad,
        @Relation(
        parentColumn = "idFacultad",
        entityColumn = "idFacultad"
    )
    val carreras: List<Carrera>
)