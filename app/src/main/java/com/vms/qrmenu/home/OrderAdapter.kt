package com.vms.qrmenu.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vms.qrmenu.R
import com.vms.qrmenu.order_details.OrderDetailsActivity

class OrderAdapter(
    private val orderList: List<Order>,
    private val onAcceptClick: (Order) -> Unit,
    private val onRejectClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTableNumber: TextView = itemView.findViewById(R.id.txt_table_number)
        val txtNumberOfItems: TextView = itemView.findViewById(R.id.txt_number_of_items)
        val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        val txtTotalAmount: TextView = itemView.findViewById(R.id.txt_total_amount)
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
        val txtPhoneNumber: TextView = itemView.findViewById(R.id.txt_phone_number)
        val txtAccept: TextView = itemView.findViewById(R.id.txt_accept)
        val txtReject: TextView = itemView.findViewById(R.id.txt_reject)
        val imgAccept: ImageView = itemView.findViewById(R.id.img_accept)
        val imgReject: ImageView = itemView.findViewById(R.id.img_reject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_orders, parent, false) // name your XML item_order.xml
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]

        holder.txtTableNumber.text = order.tableNumber
        holder.txtNumberOfItems.text = order.numberOfItems
        holder.txtTime.text = order.time
        holder.txtTotalAmount.text = order.totalAmount
        holder.txtName.text = order.customerName
        holder.txtPhoneNumber.text = order.phoneNumber

        // Accept button click
        holder.txtAccept.setOnClickListener { onAcceptClick(order) }
        holder.imgAccept.setOnClickListener { onAcceptClick(order) }

        // Reject button click
        holder.txtReject.setOnClickListener { onRejectClick(order) }
        holder.imgReject.setOnClickListener { onRejectClick(order) }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, OrderDetailsActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = orderList.size
}
