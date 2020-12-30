package com.uca.app_css.utilities

import com.google.firebase.auth.FirebaseAuth

object AppConstants {
    val PROJECT_KEY = "ProjectKey"
    val LOGOUT = "Se ha cerrado sesión correctamente"
    val LOGIN = "Se ha iniciado sesión correctamente"
    val NOTEMPTY = "Este campo no puede ir vacío"
    val MAILPASSINCORRECT = "Correo o contraseña incorrectos"
    val USER_CARNET = FirebaseAuth.getInstance().currentUser!!.email!!.substring(0,8)
}