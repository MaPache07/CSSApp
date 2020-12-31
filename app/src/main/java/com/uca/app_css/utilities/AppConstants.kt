package com.uca.app_css.utilities

object AppConstants {
    val PROJECT_KEY = "ProjectKey"
    val LOGOUT = "Se ha cerrado sesión correctamente"
    val LOGIN = "Se ha iniciado sesión correctamente"
    val NOTEMPTY = "Este campo no puede ir vacío"
    val MAILPASSINCORRECT = "Correo o contraseña incorrectos"
    val NOT_CONNECTED = "No está conectado a internet"
    val APPLIED = "Se ha aplicado correctamente al proyecto"
    val NOTAPPLIED = "No se ha podido aplicar al proyecto"
    val NOTAPPLIEDAGAIN = "Ya ha aplicado a este proyecto"
    val FLAG_APPLY = "FlagApply"

    val STATE_PENDING = "Pendiente"
    val STATE_ACCEPTED = "Aceptado"
    val STATE_REJECTED = "Rechazado"

    private var USER_CARNET = "" //FirebaseAuth.getInstance().currentUser!!.email!!.substring(0,8)
    private var ID_ESTUDIANTE = -1

    fun setUserCarnet(carnet: String){
        this.USER_CARNET = carnet
    }
    fun getUserCarnet() = this.USER_CARNET
    fun setIdEstudiante(idEstudiante: Int){
        this.ID_ESTUDIANTE = idEstudiante
    }
    fun getIdEstudiante() = this.ID_ESTUDIANTE
}