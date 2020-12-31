package com.uca.app_css.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uca.app_css.database.daos.*
import com.uca.app_css.database.entities.*

@Database(entities = arrayOf(Admin::class, Carrera::class, Estudiante::class, Facultad::class,
        Perfil::class, Proyecto::class, ProyectoXCarrera::class, ProyectoXEstudiante::class), version = 1)
public abstract class RoomDB : RoomDatabase() {

    abstract fun adminDao(): AdminDAO
    abstract fun carreraDao(): CarreraDAO
    abstract fun estudianteDao(): EstudianteDAO
    abstract fun facultadDao(): FacultadDAO
    abstract fun perfilDao(): PerfilDAO
    abstract fun proyectoDao(): ProyectoDAO
    abstract fun proyectoXCarreraDao(): ProyectoXCarreraDAO
    abstract fun proyectoXEstudianteDao(): ProyectoXEstudianteDAO

    companion object{

        @Volatile
        private var INSTANCE : RoomDB? = null

        fun getDatabase(context: Context) : RoomDB{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "proyect_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}