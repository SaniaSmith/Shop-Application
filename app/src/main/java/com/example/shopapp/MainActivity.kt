package com.example.shopapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myProductRecyclerView: RecyclerView
    lateinit var myProductAdapter: productAdapter
    lateinit var cartButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cartButton = findViewById(R.id.iv_cart)
        myProductRecyclerView = findViewById(R.id.rv_products)

        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiInterface::class.java)

        var retrofitProductData = retrofitBuilder.getProductData()

        retrofitProductData.enqueue(object : Callback<productData?> {
            override fun onResponse(call: Call<productData?>, response: Response<productData?>) {
                var dataProductList = response.body()!!

                myProductAdapter = productAdapter(this@MainActivity, dataProductList)
                myProductRecyclerView.adapter = myProductAdapter
                myProductRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("TAG : onResponse", "onResponse : " + response.body())
            }

            override fun onFailure(call: Call<productData?>, t: Throwable) {
                Log.d("TAG: onFailure", "onResponse : " + t.message)
            }
        })

        cartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}