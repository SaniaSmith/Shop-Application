package com.example.shopapp

import retrofit2.Call
import retrofit2.http.GET

interface ProductApiInterface {
    @GET("products")
    fun getProductData() : Call<productData>
}