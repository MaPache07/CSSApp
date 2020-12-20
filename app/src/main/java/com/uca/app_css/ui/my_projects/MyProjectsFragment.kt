package com.uca.app_css.ui.my_projects

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.R
import com.uca.app_css.ui.login.LoginFragment


class MyProjectsFragment : Fragment(){

    private lateinit var myProjectsViewModel: MyProjectsViewModel

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myProjectsViewModel =
                ViewModelProvider(this).get(MyProjectsViewModel::class.java)
        mAuth = FirebaseAuth.getInstance()
        val root = inflater.inflate(R.layout.fragment_my_projects, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        val parentFrag = MyProjectsFragment()

        if(mAuth.currentUser == null){

            val childFragment: Fragment = LoginFragment()
            childFragmentManager.beginTransaction().replace(R.id.child_fragment_container, childFragment).commit()
        }
        else{
            val childFragment: Fragment = LoginFragment()
            childFragmentManager.beginTransaction().replace(R.id.child_fragment_container, childFragment).commit()
        }
        /*myProjectsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
}