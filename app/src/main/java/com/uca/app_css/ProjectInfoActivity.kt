package com.uca.app_css

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY

class ProjectInfoActivity : AppCompatActivity() {

    private lateinit var nameTxt: TextView
    private lateinit var onChargeTxt: TextView
    private lateinit var typeTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var durationTxt: TextView
    private lateinit var cuposTxt: TextView
    private lateinit var applyBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        bindData()
    }

    fun bindData(){
        val proyecto : Proyecto? = intent.extras!!.getParcelable(PROJECT_KEY)

        nameTxt = findViewById(R.id.project_name)
        onChargeTxt = findViewById(R.id.on_charge)
        durationTxt = findViewById(R.id.project_duration)
        typeTxt = findViewById(R.id.project_type)
        cuposTxt = findViewById(R.id.project_cupos)
        descriptionTxt = findViewById(R.id.project_description)
        applyBtn = findViewById(R.id.apply_btn)
        applyBtn.setOnClickListener(clickListener)

        nameTxt.text = proyecto!!.nombre
        onChargeTxt.text = proyecto.encargado
        durationTxt.text = "Duración: " + proyecto.fecha_inicio + " a " + proyecto.fecha_fin
        typeTxt.text = "Tipo: " + proyecto.tipo_horas
        cuposTxt.text = "Cupos: " + proyecto.cupos.toString()
        descriptionTxt.text = proyecto.descripcion
    }

    val clickListener = View.OnClickListener {

    }
}