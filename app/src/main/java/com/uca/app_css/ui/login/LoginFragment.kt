package com.uca.app_css.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.R
import com.uca.app_css.ui.notifications.NotificationsViewModel

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var emailBox: EditText
    private lateinit var pswdBox: EditText
    private lateinit var childFrag: Fragment

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
                ViewModelProvider(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.layout_login, container, false)

        loginBtn = root.findViewById(R.id.cirLoginButton)
        emailBox = root.findViewById(R.id.editTextEmail)
        pswdBox = root.findViewById(R.id.editTextPassword)
        loginBtn.setOnClickListener{
            var email: String = emailBox.text.toString()
            var contra: String = pswdBox.text.toString()

            if(TextUtils.isEmpty(email)){
                emailBox.setError("Este campo no puede ir vacío")
            }
            if (TextUtils.isEmpty(contra)){
                pswdBox.setError("Este campo no puede ir vacio")
            }
            mAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(getActivity(),"Inicio de sesion correcto", Toast.LENGTH_LONG).show();
                    }
                }
        }
        return root
    }
}