package com.uca.app_css.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.uca.app_css.R
//import com.bumptech.glide.Glide
import com.uca.app_css.database.entities.Proyecto

class AllProjectsAdapter(var projects: List<Proyecto>, val clickListener: (Proyecto) -> Unit): RecyclerView.Adapter<AllProjectsAdapter.ProyectoHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProyectoHolder  {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.cardview_all_projects, p0, false)
        return ProyectoHolder(view)
    }

    override fun onBindViewHolder(proyectoHolder: ProyectoHolder, pos: Int) {
        //aca para pasarle todo lo que se quiere
        proyectoHolder.bind(projects[pos],clickListener)
    }

    override fun getItemCount(): Int = projects.size

    fun dataChange(listaMatches : List<Proyecto>){
        projects = listaMatches
        notifyDataSetChanged()
    }

    inner class ProyectoHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

            fun bind(item : Proyecto, clickListener: (Proyecto) -> Unit) = with(itemView){
                itemView.findViewById<TextView>(R.id.nombre_project_individual).text = item.nombre
                this.setOnClickListener{clickListener(item)}
            }
        }

}

