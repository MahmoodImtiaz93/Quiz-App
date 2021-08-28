package com.mahmood_imtiaz.quizapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Adapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mahmood_imtiaz.quizapp.R
import com.mahmood_imtiaz.quizapp.adapter.QuizAdapter
import com.mahmood_imtiaz.quizapp.model.Quiz
import com.mahmood_imtiaz.quizapp.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizlist = mutableListOf<Quiz>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    fun setupViews() {
        setupFireStore()
        setUpDrawerlayout()
        setUpRecyclerView()
        setUpDatePicker()
    }

    @SuppressLint("SimpleDateFormat")

    private fun setUpDatePicker() {
        btn_date_picker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
//                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
//                val date:String = dateFormatter.format(Date(it))
                val date:String=datePicker.headerText
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                showToast(date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER", "Date Picker Cancelled")
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference: CollectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                showToast("Error Fetching Data")
                return@addSnapshotListener
            }
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
            Log.d("Data", value.toObjects(Quiz::class.java).toString())
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
        navigationView.setNavigationItemSelectedListener {
            val intent=Intent(this,ProfileAvtivity::class.java)
            startActivity(intent)
            main_drawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}