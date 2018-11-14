package com.example.michael.proiectunitbv


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Intent


class LoginActivity : AppCompatActivity() {
    lateinit var mLoginBtn : Button
    lateinit var mLoginMail : TextView
    lateinit var mLoginPassword : TextView
    lateinit var mAuth : FirebaseAuth
    lateinit var mRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mRegister = findViewById(R.id.register_button)
        mLoginBtn = findViewById(R.id.login_button)
        mLoginMail = findViewById(R.id.login_mail)
        mLoginPassword  = findViewById(R.id.login_password)
        mAuth = FirebaseAuth.getInstance()

                    mRegister.setOnClickListener {
                val Intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(Intent)
                finish()
        }

// /login error messages
        mLoginBtn.setOnClickListener{

            val email = mLoginMail.text.toString().trim()
            val password = mLoginPassword.text.toString().trim()

                    //in cazul in care nu completeaza email
            if( TextUtils.isEmpty(email)) {

                mLoginMail.error = "Enter email"
                return@setOnClickListener
            }
            //in cazul in care nu completeaza pass`ul
            if( TextUtils.isEmpty(password)) {

                mLoginPassword.error = "Enter password"
                return@setOnClickListener
            }

            loginUser(email, password)

        }
    }


       private fun loginUser (email: String, password: String) {

           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this) {
                       val user = FirebaseAuth.getInstance().currentUser

                        if (user!!.isEmailVerified){
                           val startIntent = Intent(applicationContext, MainActivity::class.java)
                           startActivity(startIntent)
                           finish()
                       }else {
                            Toast.makeText(this, "Verifica mail`ul de confirmare sau date gresite de login", Toast.LENGTH_SHORT).show()
                        }

                   }
       }


}


