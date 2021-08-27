package com.mahmood_imtiaz.quizapp.utils

import com.mahmood_imtiaz.quizapp.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon6,
        R.drawable.icon7,
        R.drawable.icon8,
        R.drawable.icon9

    )
    var currentIcons = 0
    fun getColor(): Int {
        currentIcons = (currentIcons + 1) % icons.size
        return icons[currentIcons]
    }
}