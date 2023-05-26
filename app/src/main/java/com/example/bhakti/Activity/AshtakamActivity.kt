package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bhakti.Adapter.AshtakamAdapter
import com.example.bhakti.ModelClass.AshtakamModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityAshtakamBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class AshtakamActivity : AppCompatActivity() {

    private lateinit var adapter: AshtakamAdapter
    private lateinit var contentBinding: ActivityAshtakamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this,R.layout.activity_ashtakam)

        contentBinding.ashtakamRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        contentBinding.ashtakamRecyclerView.itemAnimator = null

        val options: FirebaseRecyclerOptions<AshtakamModel> = FirebaseRecyclerOptions.Builder<AshtakamModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("ashtakam"), AshtakamModel::class.java)
            .build()


        adapter = AshtakamAdapter(options)
        contentBinding.ashtakamRecyclerView.adapter = adapter


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