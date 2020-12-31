package com.uca.app_css.utilities

import com.google.firebase.auth.FirebaseAuth

object AppConstants {
    val PROJECT_KEY = "ProjectKey"
    val LOGOUT = "Se ha cerrado sesión correctamente"
    val LOGIN = "Se ha iniciado sesión correctamente"
    val NOTEMPTY = "Este campo no puede ir vacío"
    val MAILPASSINCORRECT = "Correo o contraseña incorrectos"
    val APPLIED = "Se ha aplicado correctamente al proyecto"
    val NOTAPPLIED = "No se ha podido aplicar al proyecto"
    val NOTAPPLIEDAGAIN = "Ya ha aplicado a este proyecto"
    val APPLYERROR = "Ocurrio un error"
    val USER_CARNET = FirebaseAuth.getInstance().currentUser!!.email!!.substring(0,8)

    private var ID_ESTUDIANTE = -1

    fun setIdEstudiante(idEstudiante: Int){
        this.ID_ESTUDIANTE = idEstudiante
    }
    fun getIdEstudiante() = this.ID_ESTUDIANTE
}