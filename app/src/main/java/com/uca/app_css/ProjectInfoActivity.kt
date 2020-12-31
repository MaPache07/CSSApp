package com.uca.app_css

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXEstudiante
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY
import com.uca.app_css.utilities.AppConstants.USER_CARNET
import com.uca.app_css.utilities.AppConstants.getIdEstudiante
import java.text.SimpleDateFormat
import java.util.*

class ProjectInfoActivity : AppCompatActivity() {

    private lateinit var nameTxt: TextView
    private lateinit var onChargeTxt: TextView
    private lateinit var typeTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var durationTxt: TextView
    private lateinit var cuposTxt: TextView
    private lateinit var applyBtn: Button
    private lateinit var projectViewModel: ProyectViewModel
    private lateinit var proyecto: Proyecto

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        bindData()
    }

    fun bindData(){
        proyecto = intent.extras!!.getParcelable(PROJECT_KEY)!!
        projectViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)

        nameTxt = findViewById(R.id.project_name)
        onChargeTxt = findViewById(R.id.on_charge)
        durationTxt = findViewById(R.id.project_duration)
        typeTxt = findViewById(R.id.project_type)
        cuposTxt = findViewById(R.id.project_cupos)
        descriptionTxt = findViewById(R.id.project_description)
        applyBtn = findViewById(R.id.apply_btn)
        applyBtn.setOnClickListener(clickListener)

        nameTxt.text = proyecto.nombre
        onChargeTxt.text = proyecto.encargado
        durationTxt.text = "Duración: ${proyecto.fecha_inicio} a ${proyecto.fecha_fin}"
        typeTxt.text = "Tipo: ${proyecto.tipo_horas}"
        cuposTxt.text = "Cupos: ${proyecto.cupos}"
        descriptionTxt.text = proyecto.descripcion
    }

    @SuppressLint("SimpleDateFormat")
    val clickListener = View.OnClickListener {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if(activeNetwork != null && activeNetwork.isConnected){
            val date = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().time)
            var flagNotApplied = true
            var flagApplied = true
            projectViewModel.getProyectoXEstudianteById(proyecto.idProyecto, getIdEstudiante()).observe(this, {
                if(it == null){
                    projectViewModel.insertProyectoXEstudiante(ProyectoXEstudiante(0, proyecto.idProyecto, getIdEstudiante(), date, 0, date, USER_CARNET))
                    flagNotApplied = false
                    projectViewModel.getProyectoXEstudianteById(proyecto.idProyecto, getIdEstudiante()).observe(this, {
                        if(it != null && flagApplied){
                            projectViewModel.postProyectoXEstudiante(it)
                            flagApplied = false
                            Toast.makeText(this, AppConstants.APPLIED, Toast.LENGTH_SHORT).show()
                            return@observe
                        }
                    })
                }
                else if(flagNotApplied){
                    Toast.makeText(this, AppConstants.NOTAPPLIEDAGAIN, Toast.LENGTH_SHORT).show()
                }
                return@observe
            })
        }
        else{
            Toast.makeText(this, AppConstants.NOTAPPLIED, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}