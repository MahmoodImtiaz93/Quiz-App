package com.mahmood_imtiaz.quizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mahmood_imtiaz.quizapp.R
import kotlinx.android.synthetic.main.activity_profile_avtivity.*

class ProfileAvtivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_avtivity)
        firebaseAuth = FirebaseAuth.getInstance()
        txtEmail.text=firebaseAuth.currentUser?.email

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}