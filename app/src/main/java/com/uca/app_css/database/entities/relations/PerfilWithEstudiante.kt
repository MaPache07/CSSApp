package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.uca.app_css.database.entities.Estudiante
import com.uca.app_css.database.entities.Perfil

data class PerfilWithEstudiante(
        @Embedded val perfil: Perfil,
        @Relation(
        parentColumn = "idPerfil",
        entityColumn = "idPerfil"
    )
    val estudiantes: List<Estudiante>
)