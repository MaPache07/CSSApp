package com.uca.app_css.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProyectoWithCarrera(
    @Embedded val proyecto: Proyecto,
    @Relation(
        parentColumn = "idProyecto",
        entityColumn = "idCarrera",
        associateBy = Junction(ProyectoXCarrera::class)
    )
    val carreras: List<Carrera>
)