package com.oluwafemi.currencyconverter.model

//The object format which the requests will be received as
data class Result(
    val amount: Double,
    val error: Int,
    val error_message: String
)