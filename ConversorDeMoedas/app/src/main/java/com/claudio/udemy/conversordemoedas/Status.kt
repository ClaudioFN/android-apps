package com.claudio.udemy.conversordemoedas

data class Status(val Message: String,
                  val RecordCount: String,
                  val Result: List<ResultUSD>,
                  val Status: Int)
