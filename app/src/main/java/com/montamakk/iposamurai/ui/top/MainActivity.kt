package com.montamakk.iposamurai.ui.top

import RecyclerAdapter
import RecyclerViewHolder
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnSuccessListener
import com.montamakk.iposamurai.R.layout
import com.montamakk.iposamurai.firestore.IpoData
import com.montamakk.iposamurai.model.IpoItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        MobileAds.initialize(this, "ca-app-pub-1021270898441250~9766048267")
        var ipoList: MutableList<IpoItem>? = mutableListOf()
        IpoData().getIpoCompanies(OnSuccessListener { result ->
            //TODO :取得した結果クラスには、オブジェクト化することができない...
            //いったんループでつっこむ
            //ipoList  = result.toObjects(IpoItem::class.java)
            for(document in result){
                ipoList!!.add(IpoItem(
                    document.getString("companyName").toString(),
                    document.getString("assessment").toString(),
                    document.get("maxPrice").toString(),
                    document.get("minPrice").toString(),
                    document.get("offeringPrice").toString(),
                    document.getString("applicationStart").toString(),
                    document.getString("applicationEnd").toString(),
                    document.getString("purchaseStart").toString(),
                    document.getString("purchaseEnd").toString(),
                    document.getString("listingDate").toString()
                ))
            }
            mainRecyclerView.adapter = RecyclerAdapter(this, this, ipoList)
            mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        })
    }

    override fun onItemClick(view: View, item: IpoItem) {
        val intent = Intent(this, DetailPageActivity::class.java)
        intent.putExtra(DetailPageActivity.KEY_STATE,item)
        startActivity(intent)
    }
}