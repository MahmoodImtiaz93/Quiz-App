package com.mahmood_imtiaz.quizapp.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Adapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.adapter.QuizAdapter
import com.mahmood_imtiaz.quizapp.model.Quiz
import com.mahmood_imtiaz.quizapp.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizlist = mutableListOf<Quiz>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         populateData()
        setupViews()


    }

    private fun populateData() {

        quizlist.add(Quiz("13-Mar-21", "Quiz 2"))
        quizlist.add(Quiz("14-Mar-21", "Quiz 3"))
        quizlist.add(Quiz("15-Mar-21", "Quiz 4"))
    }

    fun setupViews() {
        setupFireStore()
        setUpDrawerlayout()
        setUpRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference =firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value,error->
           if (value==null||error!=null){
               showToast("Error Fetching Data")
               return@addSnapshotListener
           }
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
            Log.d("Data",value.toObjects(Quiz::class.java).toString())
        }
    }

    fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizlist)
        quiz_recyclerview.layoutManager = GridLayoutManager(this, 2)
        quiz_recyclerview.adapter = adapter
    }

    fun setUpDrawerlayout() {
        setSupportActionBar(topAppBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, main_drawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}