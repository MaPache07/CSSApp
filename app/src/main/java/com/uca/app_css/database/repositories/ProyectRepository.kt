package com.uca.app_css.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.uca.app_css.database.daos.*
import com.uca.app_css.database.entities.*

class ProyectRepository(private val adminDAO: AdminDAO, private val carreraDAO: CarreraDAO, private val estudianteDAO: EstudianteDAO,
                        private val facultadDAO: FacultadDAO, private val perfilDAO: PerfilDAO, private val proyectoDAO: ProyectoDAO) {

    val allAdmin: LiveData<List<Admin>> = adminDAO.getAllAdmin()
    val allCarrera: LiveData<List<Carrera>> = carreraDAO.getAllCarrera()
    val allEstudiante: LiveData<List<Estudiante>> = estudianteDAO.getAllEstudiante()
    val allFacultad: LiveData<List<Facultad>> = facultadDAO.getAllFacultad()
    val allPerfil: LiveData<List<Perfil>> = perfilDAO.getAllPerfil()
    val allProyecto: LiveData<List<Proyecto>> = proyectoDAO.getAllProyecto()

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
        proyectoDAO.insertProyectoXCarrera(proyectoXCarrera)
    }

    @WorkerThread
    suspend fun insertProyectoXEstudiante(proyectoXEstudiante: ProyectoXEstudiante){
        proyectoDAO.insertProyectoXEstudiante(proyectoXEstudiante)
    }

    //GETs

    fun getAdmin(id: Int) = adminDAO.getAdmin(id)

    fun getCarrera(id: Int) = carreraDAO.getCarrera(id)

    fun getEstudiante(id: Int) = estudianteDAO.getEstudiante(id)

    fun getFacultad(id: Int) = facultadDAO.getFacultad(id)

    fun getPerfil(id: Int) = perfilDAO.getPerfil(id)

    fun getProyecto(id: Int) = proyectoDAO.getProyecto(id)

    //QUERYs

    fun getCarreraWithFacultad(idFacultad: Int) = carreraDAO.getCarreraWithFacultad(idFacultad)

    fun getEstudianteWithProyecto(idProyecto: Int) = estudianteDAO.getEstudianteWithProyecto(idProyecto)

    fun getPerfilWithEstudiante(idEstudiante: Int) = estudianteDAO.getPerfilWithEstudiante(idEstudiante)

    fun getProyectoWithCarrera(idCarrera: Int) = proyectoDAO.getProyectoWithCarrera(idCarrera)

    fun getProyectoWithEstudiante(idEstudiante: Int) = proyectoDAO.getProyectoWithEstudiante(idEstudiante)

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
        return proyectoDAO.nukeProyectoXCarrera()
    }

    @WorkerThread
    suspend fun nukeProyectoXEstudiante(){
        return proyectoDAO.nukeProyectoXEstudiante()
    }
}