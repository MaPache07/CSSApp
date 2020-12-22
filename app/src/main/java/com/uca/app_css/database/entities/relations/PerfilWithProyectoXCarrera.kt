package com.uca.app_css.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.uca.app_css.database.entities.Perfil
import com.uca.app_css.database.entities.ProyectoXCarrera

data class PerfilWithProyectoXCarrera(
        @Embedded val perfil: Perfil,
        @Relation(
        parentColumn = "idPerfil",
        entityColumn = "idPerfil"
    )
    val proyectosXCarreras: List<ProyectoXCarrera>
)