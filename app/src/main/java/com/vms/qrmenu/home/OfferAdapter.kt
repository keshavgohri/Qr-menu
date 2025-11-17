package com.vms.qrmenu.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vms.qrmenu.R

class OfferAdapter(private val offers: List<OfferItem>) :
    RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val offerIcon: ImageView = itemView.findViewById(R.id.offer_icon)
        val offerPercentage: TextView = itemView.findViewById(R.id.offer_percentage)
        val offerDescription: TextView = itemView.findViewById(R.id.offer_description)
        val offerCount: TextView = itemView.findViewById(R.id.txt_offer_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_offer_card, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val item = offers[position]
        holder.offerIcon.setImageResource(item.iconResId)
        holder.offerPercentage.text = item.percentageText
        holder.offerDescription.text = item.description
        holder.offerCount.text = item.count
    }

    override fun getItemCount(): Int = offers.size
}
