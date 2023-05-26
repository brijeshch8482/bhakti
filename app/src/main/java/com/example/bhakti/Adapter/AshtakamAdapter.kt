package com.example.bhakti.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.Activity.ViewPdf
import com.example.bhakti.ModelClass.AshtakamModel
import com.example.bhakti.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import de.hdodenhof.circleimageview.CircleImageView

class AshtakamAdapter(options: FirebaseRecyclerOptions<AshtakamModel>) : FirebaseRecyclerAdapter<AshtakamModel, AshtakamAdapter.ViewHolder>(
    options) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ashtakam_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, ashtakamModel: AshtakamModel) {
        holder.textName.text = ashtakamModel.getName()
        holder.textName.setOnClickListener {
            val intent = Intent(holder.textName.context, ViewPdf::class.java)
            intent.putExtra("textName", ashtakamModel.getName())
            intent.putExtra("purl", ashtakamModel.getPurl())

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.textName.context.startActivity(intent)


        }

//     change text color
        if(position==0)
            holder.textName.setBackgroundColor(Color.parseColor("#FF018786"))
        else if(position==1)
            holder.textName.setBackgroundColor(Color.parseColor("#0E4B88"))
        else if(position==2)
            holder.textName.setBackgroundColor(Color.parseColor("#42980B"))
        else if(position==3)
            holder.textName.setBackgroundColor(Color.parseColor("#A8C6F4"))
        else if(position==4)
            holder.textName.setBackgroundColor(Color.parseColor("#FF6200EE"))
        else if(position==5)
            holder.textName.setBackgroundColor(Color.parseColor("#AFB42B"))
        else if(position==6)
            holder.textName.setBackgroundColor(Color.parseColor("#FF018786"))
        else if(position==7)
            holder.textName.setBackgroundColor(Color.parseColor("#0E4B88"))
        else if(position==8)
            holder.textName.setBackgroundColor(Color.parseColor("#42980B"))
        else if(position==9)
            holder.textName.setBackgroundColor(Color.parseColor("#A8C6F4"))
        else if(position==10)
            holder.textName.setBackgroundColor(Color.parseColor("#FF6200EE"))
        else if(position==11)
            holder.textName.setBackgroundColor(Color.parseColor("#A8C6F4"))

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.ashtakam_single_row_text)

    }
}