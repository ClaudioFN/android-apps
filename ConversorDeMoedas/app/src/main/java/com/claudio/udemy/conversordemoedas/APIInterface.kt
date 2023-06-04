package com.claudio.udemy.conversordemoedas

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("https://economia.awesomeapi.com.br/last/")
    fun getData() : Call<Status>
}