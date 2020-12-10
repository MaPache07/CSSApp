package com.uca.app_css.ui.all_projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uca.app_css.R

class AllProjectsFragment : Fragment() {

    private lateinit var allProjectsViewModel: AllProjectsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allProjectsViewModel =
                ViewModelProvider(this).get(AllProjectsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_projects, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        allProjectsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}