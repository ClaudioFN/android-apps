package com.claudio.udemy.conversordemoedas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author: Made by Claudio F. N.
 * APIInterface
 * This class gives the implementation of the functions that will access the API that return the value of the day about the
 * coin that was given
 */
interface APIInterface {
    /**
     * Function getDataUSD
     * Function that is used to get the value of the Dollar on the day
     * @param idMoeda of the type String: receive the name of the coin (USD-BRL)
     * Inherit from the Call of ResultUSD that holds the configuration of the return of the coin
     */
    @GET("last/{moeda}")
    fun getDataUSD(@Path("moeda") idMoeda: String) : Call<ResultUSD>

    /**
     * Function getDataEUR
     * Function that is used to get the value of the Euro on the day
     * @param idMoeda of the type String: receive the name of the coin (EUR-BRL)
     * Inherit from the Call of ResultEUR that holds the configuration of the return of the coin
     */
    @GET("last/{moeda}")
    fun getDataEUR(@Path("moeda") idMoeda: String) : Call<ResultEUR>
}