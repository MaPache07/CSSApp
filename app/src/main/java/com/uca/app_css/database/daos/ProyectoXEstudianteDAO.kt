package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Estudiante
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXEstudiante

@Dao
interface ProyectoXEstudianteDAO {

    @Insert
    suspend fun insertProyectoXEstudiante(proyectoXEstudiante: ProyectoXEstudiante)

    @Query("SELECT * FROM proyectoXEstudiante WHERE idProyecto = :idProyecto AND idEstudiante = :idEstudiante")
    fun getProyectoXEstudianteById(idProyecto: Int, idEstudiante: Int): LiveData<ProyectoXEstudiante>

    //Retorna la lista de estudiantes que pertenecen a un determinado proyecto
    @Query("SELECT * FROM estudiante e INNER JOIN proyectoXEstudiante pe ON e.idEstudiante = pe.idEstudiante " +
            "INNER JOIN proyecto p ON pe.idProyecto = p.idProyecto WHERE p.idProyecto = :idProyecto")
    fun getEstudianteWithProyecto(idProyecto: Int): LiveData<List<Estudiante>>

    //Retorna la lista de proyectos de un determinado estudiante
    @Query("SELECT * FROM proyecto p INNER JOIN proyectoXEstudiante pe ON p.idProyecto = pe.idProyecto " +
            "INNER JOIN estudiante e ON pe.idEstudiante = e.idEstudiante WHERE e.idEstudiante = :idEstudiante")
    fun getProyectoWithEstudiante(idEstudiante: Int): LiveData<List<Proyecto>>

    @Query("DELETE FROM proyectoXEstudiante WHERE idProyecto = :idProyecto AND idEstudiante = :idEstudiante")
    suspend fun eliminarProyectoXEstudiante(idProyecto: Int, idEstudiante: Int)

    @Query("DELETE FROM proyectoXEstudiante")
    suspend fun nukeProyectoXEstudiante()
}