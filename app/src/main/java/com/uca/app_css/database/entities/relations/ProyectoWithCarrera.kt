package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXCarrera

data class ProyectoWithCarrera(
        @Embedded val proyecto: Proyecto,
        @Relation(
        parentColumn = "idProyecto",
        entityColumn = "idCarrera",
        associateBy = Junction(ProyectoXCarrera::class)
    )
    val carreras: List<Carrera>
)