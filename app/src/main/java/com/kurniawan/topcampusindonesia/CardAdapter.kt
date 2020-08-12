package com.kurniawan.topcampusindonesia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_view.view.*
import java.util.logging.Filter


class CardAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<CardAdapter.CardViewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listCampus[position])
    }

    override fun getItemCount(): Int = listCampus.size

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(campus: Campus) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(campus.img)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_campus)
                tv_name.text = campus.nama
            }
        }
    }

}