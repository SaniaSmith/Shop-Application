package com.example.shopapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class cartProductAdapter(val context: Activity, val dataCartProductAdapter: List<Product>) :
    RecyclerView.Adapter<cartProductAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cart_product, parent, false)
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataCartProductAdapter.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentCartProductData  = dataCartProductAdapter[position]

        Picasso.get().load(currentCartProductData.thumbnail).into(holder.productImage)

        holder.productName.text = currentCartProductData.title

        val stringQuantity = currentCartProductData.quantity.toString()
        holder.orderQuantity.text = stringQuantity

        val stringProductPrice = currentCartProductData.price.toString()
        holder.productPrice.text = stringProductPrice

        val stringTotalPrice = currentCartProductData.total.toString()
        holder.totalPrice.text = stringTotalPrice
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView
        val productName : TextView
        val orderQuantity : TextView
        val productPrice : TextView
        val totalPrice : TextView

        init {
            productImage = itemView.findViewById(R.id.iv_cartProduct)
            productName = itemView.findViewById(R.id.tv_cartProductName)
            orderQuantity = itemView.findViewById(R.id.tv_quantityBought)
            productPrice = itemView.findViewById(R.id.tv_cartProductPrice)
            totalPrice = itemView.findViewById(R.id.tv_TotalPrice)
        }

    }
}