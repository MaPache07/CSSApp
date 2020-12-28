package com.uca.app_css.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uca.app_css.database.RoomDB
import com.uca.app_css.database.entities.*
import com.uca.app_css.database.repositories.ProyectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProyectViewModel(private val app: Application) : AndroidViewModel(app){

    private val repository: ProyectRepository
    val allAdmin: LiveData<List<Admin>>
    val allCarrera: LiveData<List<Carrera>>
    val allEstudiante: LiveData<List<Estudiante>>
    val allFacultad: LiveData<List<Facultad>>
    val allPerfil: LiveData<List<Perfil>>
    val allProyecto: LiveData<List<Proyecto>>

    init {
        val adminDao = RoomDB.getDatabase(app).adminDao()
        val carreraDao = RoomDB.getDatabase(app).carreraDao()
        val estudianteDao = RoomDB.getDatabase(app).estudianteDao()
        val facultadDao = RoomDB.getDatabase(app).facultadDao()
        val perfilDao = RoomDB.getDatabase(app).perfilDao()
        val proyectoDao = RoomDB.getDatabase(app).proyectoDao()

        repository = ProyectRepository(adminDao, carreraDao, estudianteDao, facultadDao, perfilDao, proyectoDao)
        allAdmin = repository.allAdmin
        allCarrera = repository.allCarrera
        allEstudiante = repository.allEstudiante
        allFacultad = repository.allFacultad
        allPerfil = repository.allPerfil
        allProyecto = repository.allProyecto
    }

    //INSERTS

    fun insertAdmin(admin: Admin) = viewModelScope.launch(Dispatchers.IO){
        repository.insertAdmin(admin)
    }

    fun insertCarrera(carrera: Carrera) = viewModelScope.launch(Dispatchers.IO){
        repository.insertCarrera(carrera)
    }

    fun insertEstudiante(estudiante: Estudiante) = viewModelScope.launch(Dispatchers.IO){
        repository.insertEstudiante(estudiante)
    }

    fun insertFacultad(facultad: Facultad) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFacultad(facultad)
    }

    fun insertPerfil(perfil: Perfil) = viewModelScope.launch(Dispatchers.IO){
        repository.inserPerfil(perfil)
    }

    fun insertProyecto(proyecto: Proyecto) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProyecto(proyecto)
    }

    fun insertProyectoXCarrera(proyectoXCarrera: ProyectoXCarrera) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProyectoXCarrera(proyectoXCarrera)
    }

    fun insertProyectoXEstudiante(proyectoXEstudiante: ProyectoXEstudiante) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProyectoXEstudiante(proyectoXEstudiante)
    }

    //GETs

    fun getAdmin(id: Int) = repository.getAdmin(id)

    fun getCarrera(id: Int) = repository.getCarrera(id)

    fun getEstudiante(id: Int) = repository.getEstudiante(id)

    fun getFacultad(id: Int) = repository.getFacultad(id)

    fun getPerfil(id: Int) = repository.getPerfil(id)

    fun getProyecto(id: Int) = repository.getProyecto(id)

    //QUERYs

    fun getCarreraWithFacultad(idFacultad: Int) = repository.getCarreraWithFacultad(idFacultad)

    fun getEstudianteWithProyecto(idProyecto: Int) = repository.getEstudianteWithProyecto(idProyecto)

    fun getPerfilWithEstudiante(idEstudiante: Int) = repository.getPerfilWithEstudiante(idEstudiante)

    fun getProyectoWithCarrera(idCarrera: Int) = repository.getProyectoWithCarrera(idCarrera)

    fun getProyectoWithEstudiante(idEstudiante: Int) = repository.getProyectoWithEstudiante(idEstudiante)

    //GETFireBase

    fun getAllFacultadAsync() = viewModelScope.launch {
        val response = repository.getAllFacultadAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukeFacultad()
            response.forEach {
                this@ProyectViewModel.insertFacultad(it)
            }
        }
    }

    fun getAllCarreraAsync() = viewModelScope.launch {
        val response = repository.getAllCarreraAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukeCarrera()
            response.forEach {
                this@ProyectViewModel.insertCarrera(it)
            }
        }
    }

    fun getAllProyectoAsync() = viewModelScope.launch {
        val response = repository.getAllProyectoAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukeProyecto()
            response.forEach {
                this@ProyectViewModel.insertProyecto(it)
            }
        }
    }



    fun getAllPerfilAsync() = viewModelScope.launch {
        val response = repository.getAllPerfilAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukePerfil()
            response.forEach {
                this@ProyectViewModel.insertPerfil(it)
            }
        }
    }

    fun getAllProyectoXCarreraAsync() = viewModelScope.launch {
        val response = repository.getAllProyectoXCarreraAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukeProyectoXCarrera()
            response.forEach {
                this@ProyectViewModel.insertProyectoXCarrera(it)
            }
        }
    }

    fun getAllAdminAsync() = viewModelScope.launch {
        val response = repository.getAllAdminAsync()
        if(response.isNotEmpty()){
            this@ProyectViewModel.nukeAdmin()
            response.forEach {
                this@ProyectViewModel.insertAdmin(it)
            }
        }
    }

    //NukeTables

    private suspend fun nukeAdmin() = repository.nukeAdmin()

    private suspend fun nukeCarrera() = repository.nukeCarrera()

    private suspend fun nukeEstudiante() = repository.nukeEstudiante()

    private suspend fun nukeFacultad() = repository.nukeFacultad()

    private suspend fun nukePerfil() = repository.nukePerfil()

    private suspend fun nukeProyecto() = repository.nukeProyecto()

    private suspend fun nukeProyectoXCarrera() = repository.nukeProyectoXCarrera()

    private suspend fun nukeProyectoXEstudiante() = repository.nukeProyectoXEstudiante()
}