package com.uca.app_css

import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val loginBtn:Button = findViewById(R.id.cirLoginButton)
        val emailBox:EditText = findViewById(R.id.editTextEmail)
        val pswdBox:EditText = findViewById(R.id.editTextPassword)

        loginBtn.setOnClickListener{
            var email: String = emailBox.text.toString()
            var contra: String = pswdBox.text.toString()

            if(TextUtils.isEmpty(email)){
                emailBox.setError("Este campo no puede ir vacío")
            }
            if (TextUtils.isEmpty(contra)){
                pswdBox.setError("Este campo no puede ir vacio")
            }
            mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Inicio de sesion correcto", Toast.LENGTH_LONG).show();
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(EXTRA_MESSAGE, "test")
                    }
                    startActivity(intent)
                }
            }
        }
    }
}