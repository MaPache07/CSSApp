package com.uca.app_css.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uca.app_css.database.RoomDB
import com.uca.app_css.database.entities.*
import com.uca.app_css.database.repositories.ProyectRepository
import com.uca.app_css.ui.activities.MainActivity
import com.uca.app_css.utilities.AppConstants.pref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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
        val proyectoXCarreraDAO = RoomDB.getDatabase(app).proyectoXCarreraDao()
        val proyectoXEstudianteDAO = RoomDB.getDatabase(app).proyectoXEstudianteDao()

        repository = ProyectRepository(adminDao, carreraDao, estudianteDao, facultadDao, perfilDao, proyectoDao, proyectoXCarreraDAO, proyectoXEstudianteDAO)
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

    fun getEstudianteByCarnet(carnet: String) = repository.getEstudianteByCarnet(carnet)

    fun getFacultad(id: Int) = repository.getFacultad(id)

    fun getPerfil(id: Int) = repository.getPerfil(id)

    fun getProyecto(id: Int) = repository.getProyecto(id)

    fun getProyectoXEstudianteById(idProyecto: Int, idEstudiante: Int) = repository.getProyectoXEstudianteById(idProyecto, idEstudiante)

    //QUERYs

    fun getCarreraWithFacultad(idFacultad: Int) = repository.getCarreraWithFacultad(idFacultad)

    fun getEstudianteWithProyecto(idProyecto: Int) = repository.getEstudianteWithProyecto(idProyecto)

    fun getPerfilWithEstudiante(idEstudiante: Int) = repository.getPerfilWithEstudiante(idEstudiante)

    fun getCarreraWithEstudiante(idEstudiante: Int) = repository.getCarreraWithEstudiante(idEstudiante)

    fun getProyectoWithCarrera(idCarrera: Int) = repository.getProyectoWithCarrera(idCarrera)

    fun getCarreraWithPerfilWithProyecto(idProyecto: Int) = repository.getCarreraWithPerfilWithProyecto(idProyecto)

    fun getProyectoWithEstudiante(idEstudiante: Int) = repository.getProyectoWithEstudiante(idEstudiante)

    //GETFireBase

    //Extrae las facultades de Firestore y las inserta en la base de datos local
    fun getAllFacultadAsync() = viewModelScope.launch {
        val response = repository.getAllFacultadAsync()
        this@ProyectViewModel.nukeFacultad()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertFacultad(it)
            }
        }
    }

    //Extrae las carreras de Firestore y las inserta en la base de datos local
    fun getAllCarreraAsync() = viewModelScope.launch {
        val response = repository.getAllCarreraAsync()
        this@ProyectViewModel.nukeCarrera()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertCarrera(it)
            }
        }
    }

    //Extrae el estudiante y sus proyectos de Firestore e inserta los datos en las tablas respectivas
    //Ademas guarda el id del estudiante y de la carrera
    fun getEstudianteAsync(carnet: String) = viewModelScope.launch {
        val response = repository.getEstudianteAsync(carnet)
        if(response != null){
            pref.idEstudiante = response.idEstudiante
            pref.idCarrera = response.idCarrera
            this@ProyectViewModel.nukeEstudiante()
            this@ProyectViewModel.insertEstudiante(response)
            val responseProEst = repository.getProyectoXEstudianteAsync()
            this@ProyectViewModel.nukeProyectoXEstudiante()
            if(responseProEst.isNotEmpty()){
                responseProEst.forEach {
                    this@ProyectViewModel.insertProyectoXEstudiante(it)
                }
            }
        }
    }

    //Extrae los proyectos de Firestore y los inserta en la base de datos local
    fun getAllProyectoAsync() = viewModelScope.launch {
        val response = repository.getAllProyectoAsync()
        this@ProyectViewModel.nukeProyecto()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertProyecto(it)
            }
        }
    }

    //Extrae los perfiles de Firestore y los inserta en la base de datos local
    fun getAllPerfilAsync() = viewModelScope.launch {
        val response = repository.getAllPerfilAsync()
        this@ProyectViewModel.nukePerfil()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertPerfil(it)
            }
        }
    }

    //Extrae las relaciones entre proyecto y carrera de Firestore y las inserta en la base de datos local
    fun getAllProyectoXCarreraAsync() = viewModelScope.launch {
        val response = repository.getAllProyectoXCarreraAsync()
        this@ProyectViewModel.nukeProyectoXCarrera()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertProyectoXCarrera(it)
            }
        }
    }

    fun getAllAdminAsync() = viewModelScope.launch {
        val response = repository.getAllAdminAsync()
        this@ProyectViewModel.nukeAdmin()
        if(response.isNotEmpty()){
            response.forEach {
                this@ProyectViewModel.insertAdmin(it)
            }
        }
    }

    //Inserta en Firestore una nueva relacion entre un pryecto y un estudiante (Aplica a dicho proyecto)
    fun postProyectoXEstudiante(proEst: ProyectoXEstudiante) = viewModelScope.launch {
        repository.postProyectoXEstudiante(proEst)
    }

    //NukeTables
    //Metodos que eliminan el contenido de las tablas

    private suspend fun nukeAdmin() = repository.nukeAdmin()

    private suspend fun nukeCarrera() = repository.nukeCarrera()

    private suspend fun nukeEstudiante() = repository.nukeEstudiante()

    private suspend fun nukeFacultad() = repository.nukeFacultad()

    private suspend fun nukePerfil() = repository.nukePerfil()

    private suspend fun nukeProyecto() = repository.nukeProyecto()

    private suspend fun nukeProyectoXCarrera() = repository.nukeProyectoXCarrera()

    private suspend fun nukeProyectoXEstudiante() = repository.nukeProyectoXEstudiante()
}