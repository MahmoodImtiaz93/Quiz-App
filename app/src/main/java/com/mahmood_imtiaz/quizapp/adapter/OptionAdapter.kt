package com.mahmood_imtiaz.quizapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.model.Question
import com.mahmood_imtiaz.quizapp.showToast

class OptionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<OptionAdapter.QuestionViewHolder>() {

    private var options: List<String> = listOf(
        question.option1,
        question.option2,
        question.option3,
        question.option4
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return QuestionViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {
            context.showToast(options[position])
            question.userAnswer=options[position]
            notifyDataSetChanged()
        }
        if (question.userAnswer == options[position]) {
           holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }

    }

    override fun getItemCount(): Int {
        return options.size
    }


    inner class QuestionViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val optionView: TextView = itemview.findViewById(R.id.quiz_option)
    }

}