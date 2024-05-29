package com.example.shopapp

data class cartData(
    val carts: List<cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)