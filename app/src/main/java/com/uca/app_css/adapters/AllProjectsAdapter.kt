package com.uca.app_css.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uca.app_css.R
//import com.bumptech.glide.Glide
import com.uca.app_css.database.entities.Proyecto

class AllProjectsAdapter(val context: Context, val projects: List<Proyecto>): RecyclerView.Adapter<AllProjectsAdapter.ProyectoHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AllProjectsAdapter.ProyectoHolder  {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_all_projects, p0, false)
        return ProyectoHolder(view)
    }

    override fun onBindViewHolder(proyectoHolder: ProyectoHolder, pos: Int) {
        //aca para pasarle todo lo que se quiere
        //proyectoHolder.proyectoName.text =
        //en caso de poner imagen usar glide
        //Glide.with(context).load(projects.get(pos).image).into(proyectoHolder.projectsImage)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    class ProyectoHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        //funcion para que al darle click oase a la otra actividad
        //donde se detalle la info de cada proyecto

        init{
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
           /* var container: LinearLayout = v as LinearLayout
            var mIntent = Intent(v.context, ProyectoActivity::class.java)
            mIntent.putExtra("URL",(container.getChildAt(1) as TextView).hint.toString())
            v.context.startActivity(mIntent)
            */
        }

        //val proyecto = v.nombreTextView
        //para pasar la imagen con el intent
        //val proyectoImage = v.fotoImageView
        }

}

