package com.example.gbv_monitoring_tool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    var loginEmail: EditText? = null
    var loginPassword: EditText? = null
    var registerhere: TextView? = null
    var buttonlogin: Button? = null
    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.inputemail)
        loginPassword = findViewById(R.id.inputpassword)
        registerhere = findViewById(R.id.gotoRegister)
        buttonlogin = findViewById(R.id.btn_login)

        mAuth = FirebaseAuth.getInstance()

        buttonlogin!!.setOnClickListener {
            loginUser()
        }
        registerhere!!.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

    }

    private fun loginUser(){
        val email = loginEmail!!.text.toString().trim()
        val password = loginPassword!!.text.toString().trim()

        if (email.isEmpty()){
            loginEmail!!.error = "Email cannot be empty"
            loginEmail!!.requestFocus()
        }else if(password.isEmpty()){
            loginPassword!!.error = "Password cannot be empty"
            loginPassword!!.requestFocus()
        }else {
            mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    task->
                if (task.isSuccessful){
                    Toast.makeText(this,"User logged in successfully",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Toast.makeText(this,"User log in failed",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}