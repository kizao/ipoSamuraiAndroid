package com.example.iposamurai

import RecyclerAdapter
import RecyclerViewHolder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipos  = mutableListOf<String>()
        val db = FirebaseFirestore.getInstance()
        var TAG =""
        db.collection("ipoCompanies")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val companyName : String = document.getString("companyName").toString()
                    val assessment : String = document.getString("assessment").toString()
                    val companyNameAssessment : String = companyName + "(" + assessment + ")"
                    ipos.add(companyNameAssessment)
                    Log.d(TAG, "DocumentSnapshot data: ${companyName}")
                }
                mainRecyclerView.adapter = RecyclerAdapter(this, this, ipos)
                mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext, "position $position was tapped", Toast.LENGTH_SHORT).show()
    }
}