package com.example.shopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: cartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        myRecyclerView = findViewById(R.id.rv_orderCart)

        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartAPIInterface::class.java)

        var retrofitCartData = retrofitBuilder.getCartData()

        retrofitCartData.enqueue(object : Callback<cartData?> {
            override fun onResponse(call: Call<cartData?>, response: Response<cartData?>) {
                var dataCartList = response.body()!!

                myAdapter = cartAdapter(this@CartActivity, dataCartList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@CartActivity)

                Log.d("TAG : onResponse", "onResponse : " + response.body())
            }

            override fun onFailure(call: Call<cartData?>, t: Throwable) {
                Log.d("TAG: onFailure", "onResponse : " + t.message)
            }
        })
    }
}