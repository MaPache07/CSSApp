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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.MainActivity
import com.uca.app_css.R

class SettingsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var mAuth: FirebaseAuth
    private lateinit var pfp: ImageView
    private lateinit var mail: TextView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val logOutBtn:Button = root.findViewById(R.id.logout_btn)
        mAuth = FirebaseAuth.getInstance()
        pfp = root.findViewById(R.id.profile_pic)
        mail = root.findViewById(R.id.info_email)

        logOutBtn.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(activity,"Cierre de sesión correcto", Toast.LENGTH_LONG).show();
            val intent = Intent(activity, MainActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "test")
            }
            startActivity(intent)
        }

        mail.text = mAuth.currentUser!!.email
        Log.d("testing", "callate kk")
        

        return root
    }
}