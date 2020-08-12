package com.kurniawan.topcampusindonesia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.grid_campus.view.*

class GridAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.grid_campus, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listCampus[position])
    }

    override fun getItemCount(): Int = listCampus.size

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(campus: Campus) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(campus.img)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_campus)
            }
        }
    }
}