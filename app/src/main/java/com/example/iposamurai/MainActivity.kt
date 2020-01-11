package com.example.iposamurai

import RecyclerAdapter
import RecyclerViewHolder
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    private var ipos: MutableList<IpoData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ipos = mutableListOf()
        // firesotreから情報を取得する
        val db = FirebaseFirestore.getInstance()
        db.collection("ipoCompanies").orderBy("listingDate", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val companyName : String = document.getString("companyName").toString()
                    val assessment : String = document.getString("assessment").toString()
                    val companyNameAssessment : String = companyName + "(" + assessment + ")"
                    var maxPrice  = document.get("maxPrice")
                    var minPrice  = document.get("minPrice")
                    var offeringPrice = document.get("offeringPrice").toString()
                    var initialPrice = document.get("initialPrice").toString()
                    var applicationStart : String = document.getString("applicationStart").toString()
                    var applicationEnd : String = document.getString("applicationEnd").toString()
                    var purchaseStart : String = document.getString("purchaseStart").toString()
                    var purchaseEnd : String = document.getString("purchaseEnd").toString()
                    var listingDate : String = document.getString("listingDate").toString()

                    ipos!!.add(IpoData(
                        companyName,
                        assessment,
                        companyNameAssessment,
                        maxPrice,
                        minPrice,
                        offeringPrice,
                        initialPrice,
                        applicationStart,
                        applicationEnd,
                        purchaseStart,
                        purchaseEnd,
                        listingDate
                    ))
                    Log.d("TAG", "DocumentSnapshot data: ${companyName}")
                }
                mainRecyclerView.adapter = RecyclerAdapter(this, this, ipos)
                mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }

    override fun onItemClick(view: View, item: IpoData) {
        val intent = Intent(this, DetailPageActivity::class.java)
        intent.putExtra("item",item)
        startActivity(intent)

        Toast.makeText(applicationContext, "item $item was tapped", Toast.LENGTH_SHORT).show()
    }
}