package com.uca.app_css

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.uca.app_css.database.entities.Proyecto

class ProjectInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_info)
        //val proyecto : Proyecto = intent.extras.getParcelable<>()
        var test2: TextView = findViewById(R.id.test2)
        
    }
}