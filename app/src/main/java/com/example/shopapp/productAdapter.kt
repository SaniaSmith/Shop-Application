package com.example.shopapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class productAdapter(val context: Activity, val dataProductList : List<productDataItem>) :
    RecyclerView.Adapter<productAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_product, parent, false)
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataProductList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentProductData =dataProductList[position]
        val maxLength = 20
        val maxLengthDesc = 300

        Picasso.get().load(currentProductData.image).into(holder.productImage)

        val shortNameString = if (currentProductData.title.length > maxLength) {
            currentProductData.title.substring(0, maxLength) + "..." // Add ellipsis to indicate truncation
        } else {
            currentProductData.title // Keep the name as is if it's already within the maximum length
        }

        holder.productName.text = shortNameString
        holder.productCategory.text = currentProductData.category

        val stringPrice = currentProductData.price.toString()
        holder.productPrice.text = stringPrice

        val shortDescString = if (currentProductData.description.length > maxLengthDesc) {
            currentProductData.description.substring(0, maxLength) + "..."
        } else {
            currentProductData.description
        }

        holder.expandCard.setOnClickListener {
            if (holder.productDesc.visibility == View.GONE) {
                holder.productDesc.visibility = View.VISIBLE
                holder.productDesc.text = shortDescString
                holder.expandCard.text = "Show Less"
                holder.cardHolder.layoutParams.height = 550
                holder.productDesc.layoutParams.height = 208
            } else if (holder.productDesc.visibility == View.VISIBLE) {
                holder.productDesc.visibility = View.GONE
                holder.expandCard.text = "View More"
                holder.productDesc.layoutParams.height = 104
                holder.cardHolder.layoutParams.height = 250
            }
        }

    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView
        val productName : TextView
        val productCategory : TextView
        val productPrice : TextView
        val expandCard : TextView
        val productDesc : TextView
        val cardHolder : CardView

        init {
            productImage = itemView.findViewById(R.id.iv_productImage)
            productName = itemView.findViewById(R.id.tv_productName)
            productCategory = itemView.findViewById(R.id.tv_productCategory)
            productPrice = itemView.findViewById(R.id.tv_productPrice)
            expandCard = itemView.findViewById(R.id.tv_viewMore)
            productDesc = itemView.findViewById(R.id.tv_descProduct)
            cardHolder = itemView.findViewById(R.id.cv_product)
        }

    }
}