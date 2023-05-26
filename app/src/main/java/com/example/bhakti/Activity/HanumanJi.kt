package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Adapter.HanumanAdapter
import com.example.bhakti.ModelClass.HanumanModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityHanumanJiBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class HanumanJi : AppCompatActivity() {

    private lateinit var adapter: HanumanAdapter
    private lateinit var contentBinding: ActivityHanumanJiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_hanuman_ji)

        contentBinding.hanumanRecyclerView.layoutManager = LinearLayoutManager(this)
        contentBinding.hanumanRecyclerView.itemAnimator = null

        val options: FirebaseRecyclerOptions<HanumanModel> = FirebaseRecyclerOptions.Builder<HanumanModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("hanuman"), HanumanModel::class.java)
            .build()

        adapter = HanumanAdapter(options)
        contentBinding.hanumanRecyclerView.adapter = adapter

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