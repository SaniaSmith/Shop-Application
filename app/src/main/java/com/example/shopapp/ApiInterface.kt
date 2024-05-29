package com.example.shopapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProductData() : Call<productData>

    @GET("carts")
    fun getCartsData() : Call<cartData>
}