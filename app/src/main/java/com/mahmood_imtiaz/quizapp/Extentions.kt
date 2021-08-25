package com.mahmood_imtiaz.quizapp

import android.content.Context
import android.widget.Toast
import javax.xml.datatype.Duration

fun Context.showToast(message:String,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}