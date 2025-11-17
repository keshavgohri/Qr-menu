package com.vms.qrmenu.order_history

data class OrderHistory(
    val tableNumber: String,
    val numberOfItems: String,
    val time: String,
    val totalAmount: String,
    val name: String,
    val phoneNumber: String,
    val status: String // e.g., "Accepted"
)

data class OrderSection(
    val date: String,
    val orders: List<OrderHistory>
)
