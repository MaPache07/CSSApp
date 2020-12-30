package com.uca.app_css.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uca.app_css.database.entities.Admin

@Dao
interface AdminDAO {

    @Insert
    suspend fun insert(admin: Admin)

    @Query("SELECT * FROM admin")
    fun getAllAdmin() : LiveData<List<Admin>>

    @Query("SELECT * FROM admin WHERE idAdmin = :id")
    fun getAdmin(id: Int): LiveData<Admin>

    @Query("DELETE FROM admin")
    suspend fun nukeTable()
}