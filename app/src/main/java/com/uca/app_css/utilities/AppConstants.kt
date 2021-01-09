package com.uca.app_css.utilities

object AppConstants {
    lateinit var pref: Preferences
    val PROJECT_KEY = "ProjectKey"
    val LOGOUT = "Se ha cerrado sesión correctamente"
    val LOGIN = "Se ha iniciado sesión correctamente"
    val NOTEMPTY = "Este campo no puede ir vacío"
    val MAILPASSINCORRECT = "Correo o contraseña incorrectos"
    val NOT_CONNECTED = "No está conectado a internet"
    val APPLIED = "Se ha aplicado correctamente al proyecto"
    val NOTAPPLIED = "No se ha podido aplicar al proyecto"
    val NOTAPPLIEDAGAIN = "Ya ha aplicado a este proyecto"
    val ERROR = "Ocurrió un error"
    val FLAG_APPLY = "FlagApply"

    val STATE_PENDING = "Pendiente"
    val STATE_ACCEPTED = "Aceptado"
    val STATE_REJECTED = "Rechazado"
}