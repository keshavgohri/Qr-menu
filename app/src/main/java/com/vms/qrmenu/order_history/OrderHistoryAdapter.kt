package com.vms.qrmenu.order_history

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vms.qrmenu.R
import com.vms.qrmenu.order_details.OrderDetailsActivity

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

class OrderHistoryAdapter(private val sections: List<OrderSection>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Any> = mutableListOf()

    init {
        sections.forEach { section ->
            items.add(section.date) // header
            items.addAll(section.orders) // orders
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is String) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_sticky_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_order_history, parent, false)
            OrderViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(items[position] as String)
        } else if (holder is OrderViewHolder) {
            holder.bind(items[position] as OrderHistory)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, OrderDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        private val txtOrders: TextView = itemView.findViewById(R.id.txt_orders)

        fun bind(date: String) {
            txtDate.text = date
            // Optionally, count orders for the header if needed
            txtOrders.text = "Orders: 12 (₹2340)"
        }
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTableNumber: TextView = itemView.findViewById(R.id.txt_table_number)
        private val txtNumberOfItems: TextView = itemView.findViewById(R.id.txt_number_of_items)
        private val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        private val txtTotalAmount: TextView = itemView.findViewById(R.id.txt_total_amount)
        private val txtName: TextView = itemView.findViewById(R.id.txt_name)
        private val txtPhoneNumber: TextView = itemView.findViewById(R.id.txt_phone_number)
        private val txtAccepted: TextView = itemView.findViewById(R.id.txt_accepted)


        fun bind(order: OrderHistory) {
            txtTableNumber.text = order.tableNumber
            txtNumberOfItems.text = order.numberOfItems
            txtTime.text = order.time
            txtTotalAmount.text = order.totalAmount
            txtName.text = order.name
            txtPhoneNumber.text = order.phoneNumber
            txtAccepted.text = order.status
        }
    }
}
