package com.uca.app_css.ui.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.R
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants.getUserCarnet
import com.uca.app_css.utilities.AppConstants.setUserCarnet

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var projectViewModel: ProyectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

        if (mAuth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if(activeNetwork != null && activeNetwork.isConnected){
                setUserCarnet(FirebaseAuth.getInstance().currentUser!!.email!!.substring(0,8))
                projectViewModel.getEstudianteAsync(getUserCarnet())
                projectViewModel.getAllCarreraAsync()
                projectViewModel.getAllFacultadAsync()
                projectViewModel.getAllProyectoAsync()
                projectViewModel.getAllPerfilAsync()
                projectViewModel.getAllProyectoXCarreraAsync()
            }
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_all_projects, R.id.navigation_my_projects, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
        projectViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
    }
}