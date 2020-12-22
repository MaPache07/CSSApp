package com.uca.app_css.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PerfilWithProyectoXCarrera(
    @Embedded val perfil: Perfil,
    @Relation(
        parentColumn = "idPerfil",
        entityColumn = "idPerfil"
    )
    val proyectosXCarreras: List<ProyectoXCarrera>
)