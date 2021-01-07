package com.uca.app_css.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.ui.activities.ProjectInfoActivity
import com.uca.app_css.R
import com.uca.app_css.adapters.AllProjectsAdapter
import com.uca.app_css.database.entities.Proyecto
import com.uca.app_css.database.viewmodels.ProyectViewModel
import com.uca.app_css.utilities.AppConstants
import com.uca.app_css.utilities.AppConstants.PROJECT_KEY
import com.uca.app_css.utilities.AppConstants.getIdCarrera

class AllProjectsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var allProjectsViewModel: ProyectViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: AllProjectsAdapter
    private lateinit var mSpinnerItem: MenuItem
    private lateinit var spinnerFilter: Spinner
    private lateinit var arrayFilter : Array<String>
    private lateinit var observer: Observer<List<Proyecto>>
    private var flagObserver = true
    lateinit var viewF: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewF = inflater.inflate(R.layout.fragment_all_projects, container, false)
        allProjectsViewModel = ViewModelProvider(this).get(ProyectViewModel::class.java)
        initData()

        setHasOptionsMenu(true)

        changeList()
        initRecycler(emptyList())
        return viewF
    }

    fun initData(){
        auth = FirebaseAuth.getInstance()
        arrayFilter = arrayOf(getString(R.string.filter_todos), getString(R.string.filter_mi_carrera))

        observer = Observer {
            viewAdapter.dataChange(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.all_projects_menu, menu)

        mSpinnerItem = menu.findItem(R.id.filter_all_projects)
        spinnerFilter = mSpinnerItem.actionView as Spinner
        spinnerFilter.adapter = ArrayAdapter(requireContext(),R.layout.simple_spinner_item, R.id.item_spinner, arrayFilter)

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    flagObserver = true
                    changeList()
                }
                else if(p2 == 1){
                    flagObserver = false
                    changeList()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun changeList(){
        if(flagObserver) {
            allProjectsViewModel.getProyectoWithCarrera(getIdCarrera()).removeObserver(observer)
            allProjectsViewModel.allProyecto.observe(viewLifecycleOwner, observer)
        }
        else{
            allProjectsViewModel.allProyecto.removeObserver(observer)
            allProjectsViewModel.getProyectoWithCarrera(getIdCarrera()).observe(viewLifecycleOwner, observer)
        }

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
        extras.putBoolean(AppConstants.FLAG_APPLY, false)
        startActivity(Intent(context, ProjectInfoActivity::class.java).putExtras(extras))
    }
}