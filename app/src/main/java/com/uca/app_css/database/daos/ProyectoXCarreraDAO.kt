package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXCarrera
import com.uca.app_css.models.CarreraWithPerfil

@Dao
interface ProyectoXCarreraDAO {

    @Insert
    suspend fun insertProyectoXCarrera(proyectoXCarrera: ProyectoXCarrera)

    //Retorna los proyectos de una determinada carrera
    @Query("SELECT *, p.nombre FROM proyecto p INNER JOIN proyectoXCarrera pc ON p.idProyecto = pc.idProyecto " +
            "INNER JOIN carrera c ON pc.idCarrera = c.idCarrera WHERE c.idCarrera = :idCarrera")
    fun getProyectoWithCarrera(idCarrera: Int): LiveData<List<Proyecto>>

    //Retorna un modelo que contiene los datos del perfil para cada carrera de un determinado proyecto
    @Query("SELECT c.idCarrera, pe.idPerfil, c.nombre AS carrera, pe.perfil, pe.descripcion AS perfilDesc FROM carrera c " +
            "INNER JOIN proyectoXCarrera pc ON c.idCarrera = pc.idCarrera " +
            "INNER JOIN proyecto p ON pc.idProyecto = p.idProyecto " +
            "INNER JOIN perfil pe ON pc.idPerfil = pe.idPerfil WHERE p.idProyecto = :idProyecto")
    fun getCarreraWithPerfilWithProyecto(idProyecto: Int): LiveData<List<CarreraWithPerfil>>

    @Query("DELETE FROM proyectoXCarrera")
    suspend fun nukeProyectoXCarrera()
}