package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Adapter.BholenathAdapter
import com.example.bhakti.ModelClass.BholenathModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityBholenathBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class BholenathActivity : AppCompatActivity() {

    private lateinit var adapter: BholenathAdapter
    private lateinit var contentBinding: ActivityBholenathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_bholenath)


        contentBinding.bholenathRecyclerView.layoutManager = LinearLayoutManager(this)
        contentBinding.bholenathRecyclerView.itemAnimator = null


        val options: FirebaseRecyclerOptions<BholenathModel> = FirebaseRecyclerOptions.Builder<BholenathModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("bholenath"), BholenathModel::class.java)
            .build()

        adapter = BholenathAdapter(options)
        contentBinding.bholenathRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}