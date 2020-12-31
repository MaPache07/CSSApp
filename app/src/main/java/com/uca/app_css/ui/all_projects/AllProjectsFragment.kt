package com.uca.app_css.ui.all_projects

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.ProjectInfoActivity
import com.uca.app_css.R
import com.uca.app_css.adapters.AllProjectsAdapter
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY

class AllProjectsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var allProjectsViewModel: ProyectViewModel
    private lateinit var  viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AllProjectsAdapter
    lateinit var viewF: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewF = inflater.inflate(R.layout.fragment_all_projects, container, false)
        allProjectsViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
        auth = FirebaseAuth.getInstance()
        changeList()
        initRecycler(emptyList())
        return viewF
    }

    fun changeList(){
        allProjectsViewModel.allProyecto.observe(viewLifecycleOwner, { match ->
            viewAdapter.dataChange(match)
        })
    }

    fun initRecycler(match : List<Proyecto>){
        viewManager = LinearLayoutManager(context)
        viewAdapter = AllProjectsAdapter(match, true, viewLifecycleOwner, this) {matchItem: Proyecto -> onClicked(matchItem)}
        viewF.findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun onClicked(item : Proyecto){
        val extras = Bundle()
        extras.putParcelable(PROJECT_KEY, item)
        startActivity(Intent(context, ProjectInfoActivity::class.java).putExtras(extras))
    }
}