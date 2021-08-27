package com.mahmood_imtiaz.quizapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.showToast
import kotlinx.android.synthetic.main.activity_login_intro.*
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth:FirebaseAuth= FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
            showToast("Logged In")
            redirect("MAIN")
        }
        lets_go.setOnClickListener {
            redirect("LOGIN")
        }

    }
    private fun redirect(name:String){
        val intent =when(name){
            "LOGIN"->Intent(this,LoginActivity::class.java)
            "MAIN"-> Intent(this,MainActivity::class.java)
            else->throw Exception("No Path Exists")
        }
        startActivity(intent)
        finish()
    }
}