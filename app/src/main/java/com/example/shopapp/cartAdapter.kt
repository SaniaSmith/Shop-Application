package com.example.shopapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class cartAdapter(val context : Activity, val dataCartList: cartData) :
    RecyclerView.Adapter<cartAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_cart, parent, false)
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataCartList.carts.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currectCartData = dataCartList.carts[position]

        val stringOrderID = currectCartData.id.toString()
        holder.orderID.text = stringOrderID

        val stringTotalProductPrice = currectCartData.total.toString()
        holder.totalProductPrice.text = stringTotalProductPrice

        holder.productAdapter = cartProductAdapter(context, currectCartData.products)
        holder.productRecyclerView.adapter = holder.productAdapter
        holder.productRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orderID : TextView
        val totalProductPrice : TextView
        val productRecyclerView : RecyclerView
        lateinit var productAdapter : cartProductAdapter

        init {
            orderID = itemView.findViewById(R.id.tv_orderID)
            totalProductPrice = itemView.findViewById(R.id.tv_totalProductPrice)
            productRecyclerView = itemView.findViewById(R.id.rv_eachProductOrder)
        }
    }
}