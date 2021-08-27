package com.mahmood_imtiaz.quizapp.utils

object ColorPicker {
    val colors = arrayOf("#96D6FF", "#4DB8FF", "#0099FF", "#265C80", "#004D80", "#007ACC")
    var currentColor = 0
    fun getColor(): String {
        currentColor = (currentColor + 1) % colors.size
        return colors[currentColor]
    }
}