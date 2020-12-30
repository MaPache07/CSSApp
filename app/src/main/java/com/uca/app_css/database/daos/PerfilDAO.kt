package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Perfil
import com.uca.app_css.database.entities.Proyecto

@Dao
interface PerfilDAO {

    @Insert
    suspend fun insert(perfil: Perfil)

    @Query("SELECT * FROM perfil")
    fun getAllPerfil() : LiveData<List<Perfil>>

    @Query("SELECT * FROM perfil WHERE idPerfil = :id")
    fun getPerfil(id: Int): LiveData<Perfil>

    @Query("DELETE FROM perfil")
    suspend fun nukeTable()
}