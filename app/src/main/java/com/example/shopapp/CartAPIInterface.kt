package com.example.shopapp

import retrofit2.Call
import retrofit2.http.GET

interface CartAPIInterface {

    @GET("carts")
    fun getCartData() : Call<cartData>
}