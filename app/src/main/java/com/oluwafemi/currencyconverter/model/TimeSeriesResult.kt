package com.oluwafemi.currencyconverter.model

import com.google.gson.annotations.SerializedName

data class TimeSeriesResult(
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("timeseries")
    val timeSeries: Boolean,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: List<List<String>>
)
