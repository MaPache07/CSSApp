package com.uca.app_css.ui.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.uca.app_css.R
import com.uca.app_css.utilities.AppConstants.LOGIN
import com.uca.app_css.utilities.AppConstants.MAILPASSINCORRECT
import com.uca.app_css.utilities.AppConstants.NOTEMPTY
import com.uca.app_css.utilities.AppConstants.NOT_CONNECTED

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

        initData()
    }

    fun initData(){
        mAuth = FirebaseAuth.getInstance()

        loginBtn = findViewById(R.id.cirLoginButton)
        emailBox = findViewById(R.id.editTextEmail)
        pswdBox = findViewById(R.id.editTextPassword)

        loginBtn.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener {
        email= emailBox.text.toString()
        contra = pswdBox.text.toString()

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(contra)){
            if(TextUtils.isEmpty(email)) emailBox.error = NOTEMPTY
            else emailBox.error = null
            if(TextUtils.isEmpty(contra)) pswdBox.error = NOTEMPTY
            else pswdBox.error = null
            return@OnClickListener
        }

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if(activeNetwork != null && activeNetwork.isConnected){
            mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, LOGIN, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, MAILPASSINCORRECT, Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, NOT_CONNECTED, Toast.LENGTH_SHORT).show()
        }
    }
}