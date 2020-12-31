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
    fun getEstudiante(id: Int): LiveData<Estudiante>

    @Query("SELECT * FROM estudiante WHERE carnet = :carnet")
    fun getEstudianteByCarnet(carnet: String): LiveData<Estudiante>

    @Query("SELECT * FROM perfil p INNER JOIN estudiante e ON p.idPerfil = e.idPerfil " +
                "WHERE e.idEstudiante = :idEstudiante")
    fun getPerfilWithEstudiante(idEstudiante: Int): LiveData<Perfil>

    @Query("SELECT * FROM carrera c INNER JOIN estudiante e ON c.idCarrera = e.idCarrera " +
                "WHERE e.idEstudiante = :idEstudiante")
    fun getCarreraWithEstudiante(idEstudiante: Int): LiveData<Carrera>

    @Query("DELETE FROM estudiante")
    suspend fun nukeTable()
}