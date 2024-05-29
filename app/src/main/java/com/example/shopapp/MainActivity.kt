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

class MainActivity : AppCompatActivity() {

    lateinit var myProductRecyclerView: RecyclerView
    lateinit var myProductAdapter: productAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myProductRecyclerView = findViewById(R.id.rv_products)

        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retrofitProductData = retrofitBuilder.getProductData()
//        var retrofitCartData = retrofitBuilder.getCartsData()

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

//        retrofitCartData.enqueue(object : Callback<cartData?> {
//            override fun onResponse(call: Call<cartData?>, response: Response<cartData?>) {
//                var dataCartList = response.body()
//                var textView = findViewById<TextView>(R.id.tv2)
//                textView.text = dataCartList.toString()
//
//                Log.d("TAG : onResponse", "onResponse : " + response.body())
//            }
//
//            override fun onFailure(call: Call<cartData?>, t: Throwable) {
//                Log.d("TAG: onFailure", "onResponse : " + t.message)
//            }
//        })
    }
}