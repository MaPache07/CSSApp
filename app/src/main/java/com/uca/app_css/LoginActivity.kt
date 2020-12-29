package com.uca.app_css

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var emailBox: EditText
    private lateinit var pswdBox: EditText
    private lateinit var email: String
    private lateinit var contra: String

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)
        mAuth= FirebaseAuth.getInstance()
        loginBtn = findViewById(R.id.cirLoginButton)
        emailBox = findViewById(R.id.editTextEmail)
        pswdBox = findViewById(R.id.editTextPassword)

        loginBtn.setOnClickListener{
            email= emailBox.text.toString()
            contra = pswdBox.text.toString()

            if(TextUtils.isEmpty(email)){
                emailBox.setError("Este campo no puede ir vacío")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(contra)){
                pswdBox.setError("Este campo no puede ir vacio")
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Inicio de sesion correcto", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(EXTRA_MESSAGE, "test")
                    }
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Correo o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}