package com.uca.app_css.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.uca.app_css.R
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.entities.ProyectoXEstudiante
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.models.CarreraWithPerfil
import com.uca.app_css.utilities.AppConstants
import com.uca.app_css.utilities.AppConstants.ERROR
import com.uca.app_css.utilities.AppConstants.FLAG_APPLY
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY
import com.uca.app_css.utilities.AppConstants.pref
import java.text.SimpleDateFormat
import java.util.*

class ProjectInfoActivity : AppCompatActivity() {

    private lateinit var nameTxt: TextView
    private lateinit var onChargeTxt: TextView
    private lateinit var typeTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var durationTxt: TextView
    private lateinit var cuposTxt: TextView
    private lateinit var majorTxt: TextView
    private lateinit var majorPerfilTxt: TextView
    private lateinit var applyBtn: Button
    private lateinit var projectViewModel: ProyectViewModel
    private lateinit var proyecto: Proyecto
    private var flagApply: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        bindData()
    }

    //Funcion que recibe y enlaza la data con la vista
    @SuppressLint("SetTextI18n")
    fun bindData(){
        //Proyecto del cual se mostrará la información
        proyecto = intent.extras!!.getParcelable(PROJECT_KEY)!!
        //Variable que nos dice si el proyecto en cuestión ya ha sido aplicado o no
        flagApply = intent.extras!!.getBoolean(FLAG_APPLY)
        projectViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)

        nameTxt = findViewById(R.id.project_name)
        onChargeTxt = findViewById(R.id.on_charge)
        durationTxt = findViewById(R.id.project_duration)
        typeTxt = findViewById(R.id.project_type)
        cuposTxt = findViewById(R.id.project_cupos)
        descriptionTxt = findViewById(R.id.project_description)
        majorTxt = findViewById(R.id.project_major)
        majorPerfilTxt = findViewById(R.id.project_major_perfil)
        applyBtn = findViewById(R.id.apply_btn)

        //Dependiendo si el proyecto ya ha sido aplicado o no se motrará un botón para aplicar a el
        if(flagApply) applyBtn.visibility = View.INVISIBLE
        else applyBtn.setOnClickListener(clickListener)

        nameTxt.text = proyecto.nombre
        onChargeTxt.text = proyecto.encargado
        durationTxt.text = "Duración: ${proyecto.fecha_inicio} a ${proyecto.fecha_fin}"
        typeTxt.text = "Tipo: ${proyecto.tipo_horas}"
        cuposTxt.text = "Cupos: ${proyecto.cupos}"
        descriptionTxt.text = proyecto.descripcion
        majorTxt.text = "Carrera:"

        //Metodo que retorna las carreras del proyecto en cuestión con sus respectivos perfiles con el fin de mostrarlos juntos
        projectViewModel.getCarreraWithPerfilWithProyecto(proyecto.idProyecto).observe(this, {carPer ->
            if(carPer == null || carPer.isEmpty()){
                majorPerfilTxt.text = ERROR
            }
            else{
                var carrerasTxt = ""
                carPer.forEach{
                    carrerasTxt += it.carrera + " - " + it.perfilDesc + "\n"
                }
                majorPerfilTxt.text = carrerasTxt
            }
        })
    }

    //Metodo para aplicar a un proyecto
    @SuppressLint("SimpleDateFormat")
    val clickListener = View.OnClickListener {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        //Primero, se verifica que exista conexión a internet
        if(activeNetwork != null && activeNetwork.isConnected){
            //Se obtiene la fecha actual para tener registro de cuando se aplicó al proyecto
            val date = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().time)
            var flagNotApplied = true
            var flagApplied = true
            //Se verifica que el estudiante no haya aplicado al proyecto actual, buscando la relación entre estos dos (ProyectoXEstudiante) con los id actuales
            projectViewModel.getProyectoXEstudianteById(proyecto.idProyecto, pref.idEstudiante).observe(this, {
                //Si no se obtiene ningun proyecto, entonces no ha aplicado nunca y se puede proceder
                if(it == null){
                    projectViewModel.insertProyectoXEstudiante(ProyectoXEstudiante(0, proyecto.idProyecto, pref.idEstudiante, date, 0, date, pref.carnet))
                    flagNotApplied = false
                    //Debido a que se necesita el id que autogenera Room, se debe obtener el proyecto en cuestion (Con id) de la base de datos local para insertarlo en Firestore
                    projectViewModel.getProyectoXEstudianteById(proyecto.idProyecto, pref.idEstudiante).observe(this, {
                        if(it != null && flagApplied){
                            projectViewModel.postProyectoXEstudiante(it)
                            //Debido a como funcionan los observer, hay que auxiliarse de flags para evitar un doble insert
                            flagApplied = false
                            //Mensaje que informa que aplicó correctamente
                            Toast.makeText(this, AppConstants.APPLIED, Toast.LENGTH_SHORT).show()
                            return@observe
                        }
                    })
                }
                else if(flagNotApplied){
                    //Mensaje que informa que ya habia aplicado a dicho proyecto
                    Toast.makeText(this, AppConstants.NOTAPPLIEDAGAIN, Toast.LENGTH_SHORT).show()
                }
                return@observe
            })
        }
        else{
            //Mensaje que informa que no se ha podido aplicar al proyecto (Por falta de conexión a internet)
            Toast.makeText(this, AppConstants.NOTAPPLIED, Toast.LENGTH_SHORT).show()
        }
    }
}