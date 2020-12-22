package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uca.app_css.database.entities.Estudiante
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXEstudiante

data class EstudianteWithProyecto(
    @Embedded val estudiante: Estudiante,
    @Relation(
            parentColumn = "idEstudiante",
            entityColumn = "idProyecto",
            associateBy = Junction(ProyectoXEstudiante::class)
    )
        val proyectos: List<Proyecto>
)