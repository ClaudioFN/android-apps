package com.claudio.udemy.conversordemoedas

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("last/USD-BRL")
    fun getData() : Call<Result>
}