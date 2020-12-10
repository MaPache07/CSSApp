package com.uca.app_css.ui.my_projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uca.app_css.R

class MyProjectsFragment : Fragment() {

    private lateinit var myProjectsViewModel: MyProjectsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myProjectsViewModel =
                ViewModelProvider(this).get(MyProjectsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_projects, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        myProjectsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}