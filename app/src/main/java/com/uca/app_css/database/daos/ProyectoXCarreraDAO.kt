package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXCarrera

@Dao
interface ProyectoXCarreraDAO {

    @Insert
    suspend fun insertProyectoXCarrera(proyectoXCarrera: ProyectoXCarrera)

    @Query("SELECT * FROM proyecto p INNER JOIN proyectoXCarrera pc ON p.idProyecto = pc.idProyecto " +
            "INNER JOIN carrera c ON pc.idCarrera = c.idCarrera WHERE c.idCarrera = :idCarrera")
    fun getProyectoWithCarrera(idCarrera: Int): LiveData<List<Proyecto>>

    @Query("DELETE FROM proyectoXCarrera")
    suspend fun nukeProyectoXCarrera()
}