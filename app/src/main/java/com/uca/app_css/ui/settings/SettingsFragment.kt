package com.uca.app_css.ui.settings

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        logOutBtn.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(activity,"Cierre de sesión correcto", Toast.LENGTH_LONG).show();
            val intent = Intent(activity, MainActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "test")
            }
            startActivity(intent)
        }
        return root
    }
}