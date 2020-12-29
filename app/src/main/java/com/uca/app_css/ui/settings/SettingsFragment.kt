package com.uca.app_css.ui.settings

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.MainActivity
import com.uca.app_css.R

class SettingsFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewF: View
    private lateinit var pfp: ImageView
    private lateinit var mail: TextView
    private lateinit var logoutBtn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewF = inflater.inflate(R.layout.fragment_settings, container, false)
        initData()
        return viewF
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()
        pfp = viewF.findViewById(R.id.profile_pic)
        mail = viewF.findViewById(R.id.info_email)
        mail.text = mAuth.currentUser!!.email
        logoutBtn = viewF.findViewById(R.id.logout_btn)
        logoutBtn.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener {
        mAuth.signOut()
        Toast.makeText(activity,"Cierre de sesión correcto", Toast.LENGTH_LONG).show()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}