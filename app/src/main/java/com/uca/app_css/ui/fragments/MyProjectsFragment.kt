package com.uca.app_css.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.ui.activities.ProjectInfoActivity
import com.uca.app_css.R
import com.uca.app_css.adapters.ProjectsAdapter
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants
import com.uca.app_css.utilities.AppConstants.FLAG_APPLY
import com.uca.app_css.utilities.AppConstants.pref

class MyProjectsFragment : Fragment(){

    private lateinit var mAuth: FirebaseAuth
    private lateinit var projectsViewModel: ProyectViewModel
    private lateinit var  viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ProjectsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyTxt: TextView
    lateinit var viewF: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewF = inflater.inflate(R.layout.fragment_my_projects, container, false)
        projectsViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
        mAuth = FirebaseAuth.getInstance()
        emptyTxt = viewF.findViewById(R.id.text_empty)
        changeList()
        initRecylcer(emptyList())
        return viewF
    }

    //Si la lista viene con un proyecto como minimo ocultará un mensaje y mostrará el recycler
    //Dicho mensaje dice: "No se ha aplicado a ningún proyecto todavía"
    fun changeList(){
        projectsViewModel.getProyectoWithEstudiante(pref.idEstudiante).observe(viewLifecycleOwner, {match ->
            viewAdapter.dataChange(match)
            if(match.size != 0){
                recyclerView.visibility = View.VISIBLE
                emptyTxt.visibility = View.GONE
            }
            else{
                recyclerView.visibility = View.GONE
                emptyTxt.visibility = View.VISIBLE
            }
        })
    }

    //Función que inicializa el recyclerView
    fun initRecylcer(match: List<Proyecto>){
        viewManager = LinearLayoutManager(context)
        viewAdapter = ProjectsAdapter(match, false, viewLifecycleOwner, this) { matchItem: Proyecto -> onClicked(matchItem)}
        recyclerView = viewF.findViewById(R.id.recycler_my_projects)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //Método onClick que dirigirá a la pantalla de ProjectInfo con la flag true, o sea, que ya aplicó a dicho proyecto (Para no mostrar el botón de aplicar)
    fun onClicked(item: Proyecto){
        val extras = Bundle()
        extras.putParcelable(AppConstants.PROJECT_KEY, item)
        extras.putBoolean(FLAG_APPLY, true)
        startActivity(Intent(context, ProjectInfoActivity::class.java).putExtras(extras))
    }
}