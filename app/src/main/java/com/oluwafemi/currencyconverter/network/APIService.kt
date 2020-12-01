package com.oluwafemi.currencyconverter.network

/*This file contains the network layer of the app*/
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oluwafemi.currencyconverter.model.Result
import com.oluwafemi.currencyconverter.model.TimeSeriesResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*I'm using 2 APIs because the Fixer.io's free plan doesn't provide the endpoint to convert currency,
and I don't have any money to purchase the plan that provides the convert and timeseries endpoints on Fixer.io*/

//API key and base url for Fixer
private const val API_KEY = "1eed4cda0b5c9e0ea2d9444828086dff"
private const val BASE_URL = "http://data.fixer.io/api/"

//API key and base url for Amdoren
private const val CONVERT_API_KEY = "HRUnDJk7Uejq2vuCx8agBj8C9AWzQA"
private const val CONVERT_BASE_URL = "https://www.amdoren.com/api/"

val convertRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(CONVERT_BASE_URL)
    .build()

val historicalRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ConverterService {
    //calls the convert endpoint to convert from a base currency to a target currency
    @GET("currency.php")
    fun convertToAsync(
        @Query("api_key") api_key: String = CONVERT_API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int
    ) : Deferred<Result>
}

interface TimeSeriesService {
    /*
    *This contain the end point to get the data for a time series from Fixer.io.
    * I couldn't finish up on this, because I don't have money for the plan that provides the timeseries endpoint*/
    @GET("/timeseries")
    fun getRateOverAPeriodAsync(
        @Query("api_key") api_key: String = CONVERT_API_KEY,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: List<String>
    ) : Deferred<TimeSeriesResult>
}

/*This object contains reference to instances of each Interface making a request.*/
object CurrencyConverterAPI {
    val convertService: ConverterService by lazy {
        convertRetrofit.create(ConverterService::class.java)
    }
    val timeSeriesService: TimeSeriesService by lazy {
        historicalRetrofit.create(TimeSeriesService::class.java)
    }
}