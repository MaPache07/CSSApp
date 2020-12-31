package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXCarrera
import com.uca.app_css.database.entities.ProyectoXEstudiante

@Dao
interface ProyectoDAO {

    @Insert
    suspend fun insert(proyecto: Proyecto)

    @Query("SELECT * FROM proyecto")
    fun getAllProyecto() : LiveData<List<Proyecto>>

    @Query("SELECT * FROM proyecto WHERE idProyecto = :id")
    fun getProyecto(id: Int): LiveData<Proyecto>

    @Query("DELETE FROM proyecto")
    suspend fun nukeTable()
}