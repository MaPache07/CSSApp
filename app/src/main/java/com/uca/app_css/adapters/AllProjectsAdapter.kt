package com.uca.app_css.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.uca.app_css.R
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants.STATE_ACCEPTED
import com.uca.app_css.utilities.AppConstants.STATE_PENDING
import com.uca.app_css.utilities.AppConstants.STATE_REJECTED
import com.uca.app_css.utilities.AppConstants.getIdEstudiante

class AllProjectsAdapter(var projects: List<Proyecto>, val flag: Boolean, val lifecycleOwner: LifecycleOwner, val fragment: Fragment, val clickListener: (Proyecto) -> Unit): RecyclerView.Adapter<AllProjectsAdapter.ProyectoHolder>() {

    private lateinit var view: View
    private lateinit var projectsViewModel: ProyectViewModel

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProyectoHolder  {
        if(flag) view = LayoutInflater.from(p0.context).inflate(R.layout.cardview_all_projects, p0, false)
        else view = LayoutInflater.from(p0.context).inflate(R.layout.cardview_my_projects, p0, false)
        projectsViewModel = ViewModelProvider(fragment).get(ProyectViewModel::class.java)
        return ProyectoHolder(view)
    }

    override fun onBindViewHolder(proyectoHolder: ProyectoHolder, pos: Int) {
        proyectoHolder.bind(projects[pos],clickListener)
    }

    override fun getItemCount(): Int = projects.size

    fun dataChange(listaMatches : List<Proyecto>){
        projects = listaMatches
        notifyDataSetChanged()
    }

    inner class ProyectoHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item : Proyecto, clickListener: (Proyecto) -> Unit) = with(itemView){
            if(flag){
                itemView.findViewById<TextView>(R.id.item_project_name).text = item.nombre
            }
            else{
                itemView.findViewById<TextView>(R.id.my_item_project_name).text = item.nombre
                val stateTxt = findViewById<TextView>(R.id.project_state)
                projectsViewModel.getProyectoXEstudianteById(item.idProyecto, getIdEstudiante()).observe(lifecycleOwner, {
                    if(it.estado == 0) stateTxt.text = STATE_PENDING
                    else if(it.estado == 1) stateTxt.text = STATE_ACCEPTED
                    else if(it.estado == 2) stateTxt.text = STATE_REJECTED
                })
            }
            this.setOnClickListener{clickListener(item)}
        }
    }
}

