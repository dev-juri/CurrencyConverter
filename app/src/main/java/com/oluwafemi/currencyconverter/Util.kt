package com.oluwafemi.currencyconverter

import android.content.Context

fun returnDrawableInt(context: Context, currency: String?): Int {
    val currencyList = context.resources.getStringArray(R.array.currencies)
    return when (currency) {
        currencyList[0] -> R.drawable.ic_aud
        currencyList[1] -> R.drawable.ic_cad
        currencyList[2] -> R.drawable.ic_chf
        currencyList[3] -> R.drawable.ic_eur
        currencyList[4] -> R.drawable.ic_gbp
        currencyList[5] -> R.drawable.ic_hkd
        currencyList[6] -> R.drawable.ic_jpy
        currencyList[7] -> R.drawable.ic_pln
        currencyList[8] -> R.drawable.ic_sek
        currencyList[9] -> R.drawable.ic_usd
        else -> R.drawable.ic_aud
    }
}

