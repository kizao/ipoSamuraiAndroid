package com.montamakk.iposamurai.firestore

import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class IpoData {
  private val IPO_COMPANIES = "ipoCompanies"
  private val LISTING_DATE = "listingDate"

  fun getIpoCompanies(successListener: OnSuccessListener<QuerySnapshot>) {
    val db = FirebaseFirestore.getInstance()
    db.collection(IPO_COMPANIES).orderBy(LISTING_DATE, Query.Direction.DESCENDING)
      .get()
      .addOnSuccessListener(successListener)
      .addOnFailureListener { exception ->
        Log.d("TAG", "Error getting documents: ", exception)
      }
  }
}

