package com.vms.qrmenu.home

data class Order(
    val tableNumber: String,
    val numberOfItems: String,
    val time: String,
    val totalAmount: String,
    val customerName: String,
    val phoneNumber: String
)
