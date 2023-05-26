package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Adapter.DurgaAdapter
import com.example.bhakti.ModelClass.DurgaModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityMaaDurgaBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MaaDurga : AppCompatActivity() {

    private lateinit var adapter: DurgaAdapter
    private lateinit var contentBinding: ActivityMaaDurgaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_maa_durga)

        contentBinding.maaDurgaRecyclerView.layoutManager = LinearLayoutManager(this)
        contentBinding.maaDurgaRecyclerView.itemAnimator = null


        val options: FirebaseRecyclerOptions<DurgaModel> = FirebaseRecyclerOptions.Builder<DurgaModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("durga"), DurgaModel::class.java)
            .build()

        adapter = DurgaAdapter(options)
        contentBinding.maaDurgaRecyclerView.adapter = adapter
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