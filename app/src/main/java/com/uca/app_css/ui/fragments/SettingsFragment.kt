package com.uca.app_css.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.ui.activities.MainActivity
import com.uca.app_css.R
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants.LOGOUT
import com.uca.app_css.utilities.AppConstants.pref

class SettingsFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewF: View
    private lateinit var pfp: ImageView
    private lateinit var mail: TextView
    private lateinit var names: TextView
    private lateinit var major: TextView
    private lateinit var logoutBtn: Button
    private lateinit var projectsViewModel: ProyectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewF = inflater.inflate(R.layout.fragment_settings, container, false)
        projectsViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
        initData()
        return viewF
    }

    //Función que inicializa las variables a utilizar
    fun initData(){
        mAuth = FirebaseAuth.getInstance()

        pfp = viewF.findViewById(R.id.profile_pic)
        mail = viewF.findViewById(R.id.info_email)
        names = viewF.findViewById(R.id.info_names)
        major = viewF.findViewById(R.id.info_major)
        logoutBtn = viewF.findViewById(R.id.logout_btn)
        logoutBtn.setOnClickListener(clickListener)

        mail.text = mAuth.currentUser!!.email

        //Método que obtiene los datos del estudiante tomando como base el carnet
        projectsViewModel.getEstudianteByCarnet(pref.carnet).observe(viewLifecycleOwner, {
            val nombres = it.nombres + " " + it.apellidos
            names.text = nombres
            projectsViewModel.getCarrera(it.idCarrera).observe(viewLifecycleOwner, {
                major.text = it.nombre
            })
        })
    }

    //Método para cerrar sesión
    val clickListener = View.OnClickListener {
        mAuth.signOut()
        Toast.makeText(activity, LOGOUT, Toast.LENGTH_LONG).show()
        //Debido a que no se puede acceder a la aplicación sin una sesión activa, se redirigirá a la pantalla de Login
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}