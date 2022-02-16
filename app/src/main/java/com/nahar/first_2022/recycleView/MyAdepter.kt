package com.nahar.first_2022.recycleView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nahar.first_2022.ContactActivity
import com.nahar.first_2022.DashboardActivity
import com.nahar.first_2022.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class MyAdapter(
    val context: Context,
    private val newsList: ArrayList<News>
) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(
                R.layout.grid_list,
                parent, false
            )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso
            .get()
            .load(newsList[position].imageId)
            .placeholder(R.drawable.b)
            .into(holder.imageId)

        holder.title.text = newsList[position].name

        holder.gridLayout.setOnClickListener {
            context.startActivity(
                Intent(context, ContactActivity::class.java)
                    .putExtra("contact", true)
                    .putExtra("name", newsList[position].name)
                    .putExtra("imageId", newsList[position].imageId)
            )
        }
    }
}

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gridLayout: LinearLayout = view.findViewById(R.id.grid_layout)
        val imageId: CircleImageView = view.findViewById(R.id.profile_image)
        val title: TextView = view.findViewById(R.id.nameTv)
    }



