package com.uca.app_css

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY

class ProjectInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        bindData()
    }

    fun bindData(){
        val proyecto : Proyecto? = intent.extras!!.getParcelable(PROJECT_KEY)

        var test2: TextView = findViewById(R.id.nombre_project_individual)
        test2.text = proyecto!!.nombre
    }
}