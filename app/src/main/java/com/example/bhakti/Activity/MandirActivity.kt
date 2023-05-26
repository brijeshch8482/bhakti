package com.example.bhakti.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bhakti.Adapter.MandirAdapter
import com.example.bhakti.ModelClass.MandirModel
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityMandirBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MandirActivity : AppCompatActivity() {

    private lateinit var adapter: MandirAdapter
    private lateinit var contentBinding: ActivityMandirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this,R.layout.activity_mandir)

        contentBinding.mandirRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        contentBinding.mandirRecyclerView.itemAnimator = null

        val options: FirebaseRecyclerOptions<MandirModel> = FirebaseRecyclerOptions.Builder<MandirModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("mandir"), MandirModel::class.java)
            .build()

        adapter = MandirAdapter(options)
        contentBinding.mandirRecyclerView.adapter = adapter
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