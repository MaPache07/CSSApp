package com.uca.app_css.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CarreraWithProyecto(
    @Embedded val carrera: Carrera,
    @Relation(
        parentColumn = "idCarrera",
        entityColumn = "idProyecto",
        associateBy = Junction(ProyectoXCarrera::class)
    )
    val proyectos: List<Proyecto>
)