package com.claudio.udemy.conversordemoedas
/**
 * @author: Made by Claudio F. N.
 * EURBRL
 * This class is a data class for the Euro coin
 */
data class EURBRL(val code: String,
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
