package com.example.bhakti.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhakti.ModelClass.RamModel
import com.example.bhakti.R
import com.example.bhakti.Activity.ViewPdf
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import de.hdodenhof.circleimageview.CircleImageView

class RamAdapter(options: FirebaseRecyclerOptions<RamModel>) : FirebaseRecyclerAdapter<RamModel, RamAdapter.ViewHolder>(
    options)
{
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ram_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, ramModel: RamModel) {

        holder.textName.text = ramModel.getName()
            holder.image.setOnClickListener {
                val intent = Intent(holder.image.context, ViewPdf::class.java)
                intent.putExtra("textName", ramModel.getName())
                intent.putExtra("purl", ramModel.getPurl())

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                holder.image.context.startActivity(intent)


            }
        holder.textName.setOnClickListener {
            val intent = Intent(holder.image.context, ViewPdf::class.java)
            intent.putExtra("textName", ramModel.getName())
            intent.putExtra("purl", ramModel.getPurl())

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.textName.context.startActivity(intent)

        }

        //     change text color
        if(position==0)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#FF018786"))
        else if(position==1)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#0E4B88"))
        else if(position==2)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#42980B"))
        else if(position==3)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#A8C6F4"))
        else if(position==4)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#FF6200EE"))
        else if(position==5)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#AFB42B"))
        else if(position==6)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#FF018786"))
        else if(position==7)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#0E4B88"))
        else if(position==8)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#42980B"))
        else if(position==9)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#A8C6F4"))
        else if(position==10)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#FF6200EE"))
        else if(position==11)
            holder.ramLayout.setBackgroundColor(Color.parseColor("#A8C6F4"))
    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.ram_text_single_row)
        var image: CircleImageView = itemView.findViewById(R.id.ram_image_single_row)
        val ramLayout: RelativeLayout = itemView.findViewById(R.id.ram_layout)

    }

}