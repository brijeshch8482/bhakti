package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Adapter.RamAdapter
import com.example.bhakti.ModelClass.RamModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityShreeRamBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ShreeRam : AppCompatActivity() {

    private lateinit var adapter: RamAdapter
    private lateinit var contentBinding: ActivityShreeRamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this,R.layout.activity_shree_ram)

        contentBinding.ramRecyclerView.layoutManager = LinearLayoutManager(this)
        contentBinding.ramRecyclerView.itemAnimator = null


        val options: FirebaseRecyclerOptions<RamModel> = FirebaseRecyclerOptions.Builder<RamModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("ram"), RamModel::class.java)
            .build()

        adapter = RamAdapter(options)
        contentBinding.ramRecyclerView.adapter = adapter
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