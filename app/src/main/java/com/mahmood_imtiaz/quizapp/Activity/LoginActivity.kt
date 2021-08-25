package com.mahmood_imtiaz.quizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()

        btnlogin.setOnClickListener {
            login()
        }
        btn_signup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login(){
        val email: String = editTextTextEmailAddress.text.toString()
        val password: String = editTextTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            showToast("Email and Password can't be blank")
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    showToast("Login Successful")
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    showToast("Something Went Wrong")
                }
            }
    }
}