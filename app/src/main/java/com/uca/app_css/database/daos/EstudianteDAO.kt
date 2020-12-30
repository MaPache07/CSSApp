package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Carrera
import com.uca.app_css.database.entities.Estudiante
import com.uca.app_css.database.entities.Perfil
import com.uca.app_css.database.entities.Proyecto

@Dao
interface EstudianteDAO {

    @Insert
    suspend fun insert(estudiante: Estudiante)

    @Query("SELECT * FROM estudiante")
    fun getAllEstudiante() : LiveData<List<Estudiante>>

    @Query("SELECT * FROM estudiante WHERE idEstudiante = :id")
    fun getEstudiante(id: Int): Estudiante

    @Query("SELECT * FROM estudiante e INNER JOIN proyectoXEstudiante pe ON e.idEstudiante = pe.idEstudiante " +
                "INNER JOIN proyecto p ON pe.idProyecto = p.idProyecto WHERE p.idProyecto = :idProyecto")
    fun getEstudianteWithProyecto(idProyecto: Int): LiveData<List<Estudiante>>

    @Query("SELECT * FROM perfil p INNER JOIN estudiante e ON p.idPerfil = e.idPerfil " +
                "WHERE e.idEstudiante = :idEstudiante")
    fun getPerfilWithEstudiante(idEstudiante: Int): Perfil

    @Query("SELECT * FROM carrera c INNER JOIN estudiante e ON c.idCarrera = e.idCarrera " +
                "WHERE e.idEstudiante = :idEstudiante")
    fun getCarreraWithEstudiante(idEstudiante: Int): Carrera

    @Query("DELETE FROM estudiante")
    suspend fun nukeTable()
}