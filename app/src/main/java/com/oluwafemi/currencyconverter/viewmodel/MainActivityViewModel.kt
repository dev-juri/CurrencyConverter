package com.oluwafemi.currencyconverter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oluwafemi.currencyconverter.network.CurrencyConverterAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun convertTo(from: String, to: String, amount: Int) {
        //This function takes base currency, target currency and amount to be converted as parameter

        coroutineScope.launch {
            //Make a request with parameters
            val getDeferredResult = CurrencyConverterAPI.convertService.convertToAsync(from = from, to = to, amount = amount)

            try {
                val value = getDeferredResult.await()
                _result.value = value.amount.toString()
                Log.i("Conversion_log", value.toString())
            } catch (e: Exception) {
                Log.e("Conversion_log", e.toString())
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}