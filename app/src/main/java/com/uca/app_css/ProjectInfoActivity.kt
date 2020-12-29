package com.uca.app_css

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY

class ProjectInfoActivity : AppCompatActivity() {
    private lateinit var nombre: TextView
    private lateinit var encargado: TextView
    private lateinit var tipo: TextView
    private lateinit var descripcion: TextView
    private lateinit var fechaIn: TextView
    private lateinit var fechaFin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        bindData()
    }

    fun bindData(){
        val proyecto : Proyecto? = intent.extras!!.getParcelable(PROJECT_KEY)

        nombre = findViewById(R.id.nombre_project_individual)
        encargado = findViewById(R.id.encargado_project_individual)
        tipo = findViewById(R.id.tipo_project_individual)
        descripcion = findViewById(R.id.descripcion_project_individual)
        fechaIn = findViewById(R.id.fech_in_project_individual)
        fechaFin = findViewById(R.id.fech_fin_project_individual)

        nombre.text = proyecto!!.nombre
        encargado.text = proyecto.encargado
        tipo.text = proyecto.tipo_horas
        descripcion.text = proyecto.descripcion
        fechaIn.text = proyecto.fecha_inicio
        fechaFin.text = proyecto.fecha_fin
    }
}