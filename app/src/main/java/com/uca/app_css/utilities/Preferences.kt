package com.uca.app_css.utilities

import android.content.Context
import android.content.SharedPreferences

//Clase encargada de mantener la persistencia de datos muy importantes para el funcionamiento de la App (Carnet, idEstudiante y idCarrera)
class Preferences(context: Context) {

    val PREFERENCE_NAME = "sharedpreferences"
    val SHARED_CARNET = "shared_carnet"
    val SHARED_ID_ESTUDIANTE = "shared_id_estudiante"
    val SHARED_ID_CARRERA = "shared_id_carrera"
    val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)

    var carnet: String
        get() = pref.getString(SHARED_CARNET, "")!!
        set(value) = pref.edit().putString(SHARED_CARNET, value).apply()

    var idEstudiante: Int
        get() = pref.getInt(SHARED_ID_ESTUDIANTE, 0)
        set(value) = pref.edit().putInt(SHARED_ID_ESTUDIANTE, value).apply()

    var idCarrera: Int
        get() = pref.getInt(SHARED_ID_CARRERA, 0)
        set(value) = pref.edit().putInt(SHARED_ID_CARRERA, value).apply()
}