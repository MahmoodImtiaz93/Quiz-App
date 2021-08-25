package com.mahmood_imtiaz.quizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.showToast
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        btn_signup.setOnClickListener {
            singupUser()
        }
        btn_login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun singupUser() {
        val email: String = editTextTextEmailAddress_signup.text.toString()
        val password: String = editTextTextPassword_signup.text.toString()
        val confirmPassword: String = confirmPassword_signup.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            showToast("Email and Password can't be blank")
            return
        }
        if (password != confirmPassword) {
            showToast("Password and Confirm Password didn't match")
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    showToast("User Successfully Created")
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    showToast("Something went wrong!")
                }
            }
    }
}