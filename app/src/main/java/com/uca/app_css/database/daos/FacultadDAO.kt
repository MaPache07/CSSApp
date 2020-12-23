package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Facultad

@Dao
interface FacultadDAO {

    @Insert
    suspend fun insert(facultad: Facultad)

    @Query("SELECT * FROM facultad")
    fun getAllFacultad() : LiveData<List<Facultad>>

    @Query("SELECT * FROM facultad WHERE idFacultad = :id")
    fun getFacultad(id: Int): Facultad

    @Query("DELETE FROM facultad")
    suspend fun nukeTable()
}