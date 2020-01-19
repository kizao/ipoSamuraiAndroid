package com.montamakk.iposamurai.ui.top

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.montamakk.iposamurai.R.id
import com.montamakk.iposamurai.R.layout
import com.montamakk.iposamurai.model.IpoItem

class DetailPageActivity : AppCompatActivity() {

    companion object {
        val KEY_STATE = "ipoItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_detail_page)

        val companyName: TextView = findViewById(id.companyName)
        val assessment: TextView = findViewById(id.assessment)
        val minPrice: TextView = findViewById(id.minPrice)
        val maxPrice: TextView = findViewById(id.maxPrice)
        val applicationStart: TextView = findViewById(id.applicationStart)
        val applicationEnd: TextView = findViewById(id.applicationEnd)
        val offeringPrice: TextView = findViewById(id.offeringPrice)
        val purchaseStart: TextView = findViewById(id.purchaseStart)
        val purchaseEnd: TextView = findViewById(id.purchaseEnd)
        val listingDate: TextView = findViewById(id.listingDate)

        val ipo = intent.getSerializableExtra(KEY_STATE) as IpoItem
        if(ipo is IpoItem){
          companyName.text = ipo.companyName
          assessment.text = ipo.assessment
          minPrice.text = if (ipo.minPrice == "-") ipo.minPrice else ipo.minPrice + "円"
          maxPrice.text = if (ipo.maxPrice == "-") ipo.maxPrice else ipo.maxPrice + "円"
          applicationStart.text = ipo.applicationStart
          applicationEnd.text = ipo.applicationEnd
          offeringPrice.text = if (ipo.offeringPrice == "-") ipo.offeringPrice else ipo.offeringPrice + "円"
          purchaseStart.text = ipo.purchaseStart
          purchaseEnd.text = ipo.purchaseEnd
          listingDate.text = ipo.listingDate
        }
    }
}
