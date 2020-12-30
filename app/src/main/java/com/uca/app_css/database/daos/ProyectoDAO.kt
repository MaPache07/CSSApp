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

    @Insert
    suspend fun insertProyectoXCarrera(proyectoXCarrera: ProyectoXCarrera)

    @Insert
    suspend fun insertProyectoXEstudiante(proyectoXEstudiante: ProyectoXEstudiante)

    @Query("SELECT * FROM proyecto")
    fun getAllProyecto() : LiveData<List<Proyecto>>

    @Query("SELECT * FROM proyecto WHERE idProyecto = :id")
    fun getProyecto(id: Int): LiveData<Proyecto>

    @Query("SELECT * FROM proyecto p INNER JOIN proyectoXCarrera pc ON p.idProyecto = pc.idProyecto " +
                "INNER JOIN carrera c ON pc.idCarrera = c.idCarrera WHERE c.idCarrera = :idCarrera")
    fun getProyectoWithCarrera(idCarrera: Int): LiveData<List<Proyecto>>

    @Query("SELECT * FROM proyecto p INNER JOIN proyectoXEstudiante pe ON pe.idEstudiante = e.idEstudiante " +
                "INNER JOIN estudiante e ON pe.idEstudiante = e.idEstudiante WHERE e.idEstudiante = :idEstudiante")
    fun getProyectoWithEstudiante(idEstudiante: Int): LiveData<List<Proyecto>>

    @Query("DELETE FROM proyecto")
    suspend fun nukeTable()

    @Query("DELETE FROM proyectoXCarrera")
    suspend fun nukeProyectoXCarrera()

    @Query("DELETE FROM proyectoXEstudiante")
    suspend fun nukeProyectoXEstudiante()
}