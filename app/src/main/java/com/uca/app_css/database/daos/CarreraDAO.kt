package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Carrera


@Dao
interface CarreraDAO {

    @Insert
    suspend fun insert(carrera: Carrera)

    @Query("SELECT * FROM carrera")
    fun getAllCarrera() : LiveData<List<Carrera>>

    @Query("SELECT * FROM carrera WHERE idCarrera = :id")
    fun getCarrera(id: Int): Carrera

    @Query("SELECT * FROM carrera WHERE idFacultad =:id")
    fun getCarreraWithFacultad(id: Int): LiveData<List<Carrera>>

    @Query("DELETE FROM carrera")
    suspend fun nukeTable()
}