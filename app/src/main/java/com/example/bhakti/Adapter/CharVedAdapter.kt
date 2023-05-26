package com.example.bhakti.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Activity.ViewPdf
import com.example.bhakti.ModelClass.CharVedModel
import com.example.bhakti.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso


class CharVedAdapter(options: FirebaseRecyclerOptions<CharVedModel>) : FirebaseRecyclerAdapter<CharVedModel, CharVedAdapter.ViewHolder>(
    options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_ved_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, charVedModel: CharVedModel) {
        Picasso.get().load(charVedModel.getImg()).placeholder(R.drawable.book_logo).into(holder.img)
        holder.img.setOnClickListener {
            val intent = Intent(holder.img.context, ViewPdf::class.java)
//            intent.putExtra("textName", charVedModel.getName())
            intent.putExtra("purl", charVedModel.getPurl())

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.img.context.startActivity(intent)


        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.atharvaVedaSingleRow)


    }
}