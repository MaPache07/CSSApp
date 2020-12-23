package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Proyecto

@Dao
interface ProyectoDAO {

    @Insert
    suspend fun insert(proyecto: Proyecto)

    @Query("SELECT * FROM proyecto")
    fun getAllProyecto() : LiveData<List<Proyecto>>

    @Query("SELECT * FROM proyecto WHERE idProyecto = :id")
    fun getProyecto(id: Int): Proyecto

    @Query("SELECT * FROM proyecto p " +
                "INNER JOIN proyectoXCarrera pc ON p.idProyecto = pc.idProyecto " +
                "INNER JOIN carrera c ON pc.idCarrera = c.idCarrera")
    fun getProyectoWithCarrera(idCarrera: Int): LiveData<List<Proyecto>>

    @Query("SELECT * FROM proyecto p " +
                "INNER JOIN proyectoXEstudiante pe ON p.idProyecto = pe.idProyecto " +
                "INNER JOIN estudiante e ON pe.idEstudiante = e.idEstudiante")
    fun getProyectoWithEstudiante(idEstudiante: Int): LiveData<List<Proyecto>>

    @Query("DELETE FROM proyecto")
    suspend fun nukeTable()
}