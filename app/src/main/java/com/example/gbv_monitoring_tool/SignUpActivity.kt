package com.example.gbv_monitoring_tool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    var regEmail: EditText? = null
    var regPassword: EditText? = null
    var confirmPassword: EditText? = null
    var loginHere: TextView? = null
    var register: Button? = null
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        regEmail = findViewById(R.id.inputemail)
        regPassword = findViewById(R.id.inputpassword)
        confirmPassword = findViewById(R.id.confirm_password)
        loginHere = findViewById(R.id.loginInstead)
        register = findViewById(R.id.btn_signup)

        mAuth = FirebaseAuth.getInstance()

        register!!.setOnClickListener {
            createUser()
        }
        loginHere!!.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    private fun createUser(){
        val email = regEmail!!.text.toString().trim()
        val password = regPassword!!.text.toString().trim()

        if (email.isEmpty()){
            regEmail!!.setError("Email cannot be empty")
            regEmail!!.requestFocus()
        }else if(password.isEmpty()){
            regPassword!!.setError("Password cannot be empty")
            regPassword!!.requestFocus()
        }else {
            mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    task->
                if (task.isSuccessful){
                    Toast.makeText(this,"User registered successfully",
                        Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }else{
                    Toast.makeText(this,"User registration failed",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}