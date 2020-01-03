package com.example.iposamurai

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val item = intent.getSerializableExtra("item") as IpoData
        val companyName: TextView = findViewById(R.id.companyName)
        val assessment: TextView = findViewById(R.id.assessment)
        val minPrice: TextView = findViewById(R.id.minPrice)
        val maxPrice: TextView = findViewById(R.id.maxPrice)
        val applicationStart: TextView = findViewById(R.id.applicationStart)
        val applicationEnd: TextView = findViewById(R.id.applicationEnd)
        val offeringPrice: TextView = findViewById(R.id.offeringPrice)
        val purchaseStart: TextView = findViewById(R.id.purchaseStart)
        val purchaseEnd: TextView = findViewById(R.id.purchaseEnd)
        val listingDate: TextView = findViewById(R.id.listingDate)

        companyName.text = item.companyName.toString()
        assessment.text = item.assessment.toString()
        minPrice.text = item.minPrice.toString() + "円"
        maxPrice.text = item.maxPrice.toString() + "円"
        applicationStart.text = item.applicationStart.toString()
        applicationEnd.text = item.applicationEnd.toString()
        offeringPrice.text = item.offeringPrice.toString() + "円"
        purchaseStart.text = item.purchaseStart.toString()
        purchaseEnd.text = item.purchaseEnd.toString()
        listingDate.text = item.listingDate.toString()

        }
}
