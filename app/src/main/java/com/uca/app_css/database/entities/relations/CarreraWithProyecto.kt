package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXCarrera

data class CarreraWithProyecto(
        @Embedded val carrera: Carrera,
        @Relation(
        parentColumn = "idCarrera",
        entityColumn = "idProyecto",
        associateBy = Junction(ProyectoXCarrera::class)
    )
    val proyectos: List<Proyecto>
)