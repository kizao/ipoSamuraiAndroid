package com.montamakk.iposamurai.ui.top

import RecyclerAdapter
import RecyclerViewHolder
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.montamakk.iposamurai.R
import com.montamakk.iposamurai.R.layout
import com.montamakk.iposamurai.R.string
import com.montamakk.iposamurai.firestore.IpoData
import com.montamakk.iposamurai.model.IpoItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {
    lateinit var mAdView : AdView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        MobileAds.initialize(this, resources.getString(R.string.banner_ad_app_id))
        auth = FirebaseAuth.getInstance()
        signInAnonymously()
        initAd()
        initFirebaseInstance()
        initTop()

    }

    private fun initTop() {
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

    private fun initFirebaseInstance() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = getString(string.msg_token_fmt, token)
                Log.d("TAG", msg)
            })
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

    private fun initAd() {
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("success", "currentUser = ${auth.currentUser?.uid.toString()}")
                } else {
                    Log.d("error", task.exception.toString())
                }
            }
    }

    override fun onItemClick(view: View, item: IpoItem) {
        val intent = Intent(this, DetailPageActivity::class.java)
        intent.putExtra(DetailPageActivity.KEY_STATE,item)
        startActivity(intent)
    }
}