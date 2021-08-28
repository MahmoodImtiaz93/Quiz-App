package com.mahmood_imtiaz.quizapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mahmood_imtiaz.quizapp.Activity.QuestionActivity
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.model.Quiz
import com.mahmood_imtiaz.quizapp.showToast
import com.mahmood_imtiaz.quizapp.utils.ColorPicker
import com.mahmood_imtiaz.quizapp.utils.IconPicker

class QuizAdapter(val context: Context, val quizes: List<Quiz>):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewtitle.text=quizes[position].title
        holder.cardContainer.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getColor())
        holder.itemView.setOnClickListener {
            context.showToast(quizes[position].title)
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizes[position].title)
            context.startActivity(intent)
        }

     }

    override fun getItemCount(): Int {
         return quizes.size
    }

    inner class QuizViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textViewtitle:TextView=itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}
