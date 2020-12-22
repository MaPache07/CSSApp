package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uca.app_css.database.entities.Estudiante
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXEstudiante

data class ProyectoWithEstudiante(
    @Embedded val proyecto: Proyecto,
    @Relation(
        parentColumn = "idProyecto",
        entityColumn = "idEstudiante",
        associateBy = Junction(ProyectoXEstudiante::class)
    )
    val estudiantes: List<Estudiante>
)