package com.example.bhakti.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bhakti.Activity.ViewPdf
import com.example.bhakti.ModelClass.MandirModel
import com.example.bhakti.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class MandirAdapter(options: FirebaseRecyclerOptions<MandirModel>) : FirebaseRecyclerAdapter<MandirModel, MandirAdapter.ViewHolder>(
    options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mandir_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, mandirModel: MandirModel) {
        holder.textName.text = mandirModel.getName()
        Picasso.get().load(mandirModel.getImg()).placeholder(R.drawable.profile).into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(holder.image.context, ViewPdf::class.java)
            intent.putExtra("textName", mandirModel.getName())
            intent.putExtra("purl", mandirModel.getPurl())

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.image.context.startActivity(intent)


        }

        holder.textName.setOnClickListener {
            val intent = Intent(holder.image.context, ViewPdf::class.java)
            intent.putExtra("textName", mandirModel.getName())
            intent.putExtra("purl", mandirModel.getPurl())

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.image.context.startActivity(intent)

        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.mandir_single_row_text)
        var image: ImageView = itemView.findViewById(R.id.mandir_single_row_image)

    }
}