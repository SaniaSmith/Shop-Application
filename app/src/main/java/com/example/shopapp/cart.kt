package com.example.shopapp

data class cart(
    val discountedTotal: Double,
    val id: Int,
    val products: List<Product>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)