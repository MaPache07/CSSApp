package com.uca.app_css.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.uca.app_css.database.daos.*
import com.uca.app_css.database.entities.*
import com.uca.app_css.utilities.AppConstants.getIdEstudiante
import kotlinx.coroutines.tasks.await

class ProyectRepository(private val adminDAO: AdminDAO, private val carreraDAO: CarreraDAO, private val estudianteDAO: EstudianteDAO,
                        private val facultadDAO: FacultadDAO, private val perfilDAO: PerfilDAO, private val proyectoDAO: ProyectoDAO,
                        private val proyectoXCarreraDAO: ProyectoXCarreraDAO, private val proyectoXEstudianteDAO: ProyectoXEstudianteDAO) {

    val allAdmin: LiveData<List<Admin>> = adminDAO.getAllAdmin()
    val allCarrera: LiveData<List<Carrera>> = carreraDAO.getAllCarrera()
    val allEstudiante: LiveData<List<Estudiante>> = estudianteDAO.getAllEstudiante()
    val allFacultad: LiveData<List<Facultad>> = facultadDAO.getAllFacultad()
    val allPerfil: LiveData<List<Perfil>> = perfilDAO.getAllPerfil()
    val allProyecto: LiveData<List<Proyecto>> = proyectoDAO.getAllProyecto()
    val db = FirebaseFirestore.getInstance()

    //INSERTS

    @WorkerThread
    suspend fun insertAdmin(admin: Admin){
        adminDAO.insert(admin)
    }

    @WorkerThread
    suspend fun insertCarrera(carrera: Carrera){
        carreraDAO.insert(carrera)
    }

    @WorkerThread
    suspend fun insertEstudiante(estudiante: Estudiante){
        estudianteDAO.insert(estudiante)
    }

    @WorkerThread
    suspend fun insertFacultad(facultad: Facultad){
        facultadDAO.insert(facultad)
    }

    @WorkerThread
    suspend fun inserPerfil(perfil: Perfil){
        perfilDAO.insert(perfil)
    }

    @WorkerThread
    suspend fun insertProyecto(proyecto: Proyecto){
        proyectoDAO.insert(proyecto)
    }

    @WorkerThread
    suspend fun insertProyectoXCarrera(proyectoXCarrera: ProyectoXCarrera){
        proyectoXCarreraDAO.insertProyectoXCarrera(proyectoXCarrera)
    }

    @WorkerThread
    suspend fun insertProyectoXEstudiante(proyectoXEstudiante: ProyectoXEstudiante){
        proyectoXEstudianteDAO.insertProyectoXEstudiante(proyectoXEstudiante)
    }

    //GETs

    fun getAdmin(id: Int) = adminDAO.getAdmin(id)

    fun getCarrera(id: Int) = carreraDAO.getCarrera(id)

    fun getEstudiante(id: Int) = estudianteDAO.getEstudiante(id)

    fun getEstudianteByCarnet(carnet: String) = estudianteDAO.getEstudianteByCarnet(carnet)

    fun getFacultad(id: Int) = facultadDAO.getFacultad(id)

    fun getPerfil(id: Int) = perfilDAO.getPerfil(id)

    fun getProyecto(id: Int) = proyectoDAO.getProyecto(id)

    fun getProyectoXEstudianteById(idProyecto: Int, idEstudiante: Int) = proyectoXEstudianteDAO.getProyectoXEstudianteById(idProyecto, idEstudiante)

    //QUERYs

    fun getCarreraWithFacultad(idFacultad: Int) = carreraDAO.getCarreraWithFacultad(idFacultad)

    fun getPerfilWithEstudiante(idEstudiante: Int) = estudianteDAO.getPerfilWithEstudiante(idEstudiante)

    fun getCarreraWithEstudiante(idEstudiante: Int) = estudianteDAO.getCarreraWithEstudiante(idEstudiante)

    fun getProyectoWithCarrera(idCarrera: Int) = proyectoXCarreraDAO.getProyectoWithCarrera(idCarrera)

    fun getCarreraWithPerfilWithProyecto(idProyecto: Int) = proyectoXCarreraDAO.getCarreraWithPerfilWithProyecto(idProyecto)

    fun getEstudianteWithProyecto(idProyecto: Int) = proyectoXEstudianteDAO.getEstudianteWithProyecto(idProyecto)

    fun getProyectoWithEstudiante(idEstudiante: Int) = proyectoXEstudianteDAO.getProyectoWithEstudiante(idEstudiante)

    //GETFireBase

    suspend fun getAllFacultadAsync(): List<Facultad>{
        val facultades = mutableListOf<Facultad>()
        db.collection("Facultad").get().addOnSuccessListener {documents ->
            for(document in documents){
                val facultad = document.toObject(Facultad::class.java)
                facultades.add(facultad)
            }
        }.await()
        return facultades
    }

    suspend fun getAllCarreraAsync(): List<Carrera>{
        val carreras = mutableListOf<Carrera>()
        db.collection("Carrera").get().addOnSuccessListener {documents ->
            for(document in documents){
                val carrera = document.toObject(Carrera::class.java)
                carreras.add(carrera)
            }
        }.await()
        return carreras
    }

    suspend fun getEstudianteAsync(carnet: String): Estudiante? {
        var estudiante: Estudiante? = null
        db.collection("Estudiante").whereEqualTo("carnet", carnet).get().addOnSuccessListener {documents ->
            if(documents != null){
                for(document in documents){
                    estudiante = document.toObject(Estudiante::class.java)
                }
            }
        }.await()
        return estudiante
    }

    suspend fun getAllProyectoAsync(): List<Proyecto>{
        val proyects = mutableListOf<Proyecto>()
        db.collection("Proyecto").get().addOnSuccessListener {documents ->
            for(document in documents){
                val proyect = document.toObject(Proyecto::class.java)
                proyects.add(proyect)
            }
        }.await()
        return proyects
    }

    suspend fun getAllPerfilAsync(): List<Perfil>{
        val perfiles = mutableListOf<Perfil>()
        db.collection("Perfil").get().addOnSuccessListener {documents ->
            for(document in documents){
                val perfil = document.toObject(Perfil::class.java)
                perfiles.add(perfil)
            }
        }.await()
        return perfiles
    }

    suspend fun getAllProyectoXCarreraAsync(): List<ProyectoXCarrera>{
        val proxCars = mutableListOf<ProyectoXCarrera>()
        db.collection("ProyectoxCarrera").get().addOnSuccessListener {documents ->
            for(document in documents){
                val proxCar = document.toObject(ProyectoXCarrera::class.java)
                proxCars.add(proxCar)
            }
        }.await()
        return proxCars
    }

    suspend fun getProyectoXEstudianteAsync(): List<ProyectoXEstudiante>{
        val proxEsts = mutableListOf<ProyectoXEstudiante>()
        db.collection("ProyectoxEstudiante").whereEqualTo("idEstudiante", getIdEstudiante()).get().addOnSuccessListener {documents ->
            for(document in documents){
                val proxEst = document.toObject(ProyectoXEstudiante::class.java)
                proxEsts.add(proxEst)
            }
        }.await()
        return proxEsts
    }

    suspend fun getAllAdminAsync(): List<Admin>{
        val admins = mutableListOf<Admin>()
        db.collection("Admin").get().addOnSuccessListener {documents ->
            for(document in documents){
                val admin = document.toObject(Admin::class.java)
                admins.add(admin)
            }
        }.await()
        return admins
    }

    fun postProyectoXEstudiante(proEst: ProyectoXEstudiante){
        db.collection("ProyectoxEstudiante").add(proEst)
    }

    //NukeTables

    @WorkerThread
    suspend fun nukeAdmin(){
        return adminDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeCarrera(){
        return carreraDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeEstudiante(){
        return estudianteDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeFacultad(){
        return facultadDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukePerfil(){
        return perfilDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeProyecto(){
        return proyectoDAO.nukeTable()
    }

    @WorkerThread
    suspend fun nukeProyectoXCarrera(){
        return proyectoXCarreraDAO.nukeProyectoXCarrera()
    }

    @WorkerThread
    suspend fun nukeProyectoXEstudiante(){
        return proyectoXEstudianteDAO.nukeProyectoXEstudiante()
    }
}