package com.uca.app_css.ui.my_projects

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
import com.uca.app_css.ProjectInfoActivity
import com.uca.app_css.R
import com.uca.app_css.adapters.AllProjectsAdapter
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants
import com.uca.app_css.utilities.AppConstants.FLAG_APPLY
import com.uca.app_css.utilities.AppConstants.getIdEstudiante

class MyProjectsFragment : Fragment(){

    private lateinit var mAuth: FirebaseAuth
    private lateinit var projectsViewModel: ProyectViewModel
    private lateinit var  viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AllProjectsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyTxt: TextView
    lateinit var viewF: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewF = inflater.inflate(R.layout.fragment_my_projects, container, false)
        projectsViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
        mAuth = FirebaseAuth.getInstance()
        emptyTxt = viewF.findViewById(R.id.text_empty)
        changeList()
        initRecylcer(emptyList())
        return viewF
    }

    fun changeList(){
        projectsViewModel.getProyectoWithEstudiante(getIdEstudiante()).observe(viewLifecycleOwner, {match ->
            viewAdapter.dataChange(match)
            if(match.size != 0){
                recyclerView.visibility = View.VISIBLE
                emptyTxt.visibility = View.GONE
            }
        })
    }

    fun initRecylcer(match: List<Proyecto>){
        viewManager = LinearLayoutManager(context)
        viewAdapter = AllProjectsAdapter(match, false, viewLifecycleOwner, this) {matchItem: Proyecto -> onClicked(matchItem)}
        recyclerView = viewF.findViewById(R.id.recycler_my_projects)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun onClicked(item: Proyecto){
        val extras = Bundle()
        extras.putParcelable(AppConstants.PROJECT_KEY, item)
        extras.putBoolean(FLAG_APPLY, true)
        startActivity(Intent(context, ProjectInfoActivity::class.java).putExtras(extras))
    }
}