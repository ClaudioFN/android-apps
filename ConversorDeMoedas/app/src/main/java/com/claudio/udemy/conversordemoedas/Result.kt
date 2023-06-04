package com.claudio.udemy.conversordemoedas

data class Result(    val code: String,
                      val codein: String,
                      val name: String,
                      val high: String,
                      val low: String,
                      val varBid: String,
                      val pctChange: String,
                      val bid: String,
                      val ask: String,
                      val timestamp: String,
                      val create_date: String)