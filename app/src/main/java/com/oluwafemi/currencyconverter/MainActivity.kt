package com.oluwafemi.currencyconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oluwafemi.currencyconverter.databinding.ActivityMainBinding
import com.oluwafemi.currencyconverter.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val currencyList: Array<String> = resources.getStringArray(R.array.currencies)
        val firstCurrencyListAdapter =
            ArrayAdapter(applicationContext, R.layout.currency_list, currencyList)
        val secondCurrencyListAdapter =
            ArrayAdapter(applicationContext, R.layout.currency_list, currencyList.reversed())

        binding.baseCurrencyList.apply {
            setAdapter(firstCurrencyListAdapter)
            doAfterTextChanged {
                val drawable = AppCompatResources.getDrawable(this@MainActivity, returnDrawableInt(applicationContext, it.toString()))
                binding.dropdownBase.startIconDrawable = drawable
                binding.baseCurrency.suffixText = it.toString()
            }
        }
        binding.secondCurrencyList.apply {
            setAdapter(secondCurrencyListAdapter)
            doAfterTextChanged {
                val drawable = AppCompatResources.getDrawable(this@MainActivity, returnDrawableInt(applicationContext, it.toString()))
                binding.dropdownSecond.startIconDrawable = drawable
                binding.secondCurrency.suffixText = it.toString()
            }
        }

        binding.convertBtn.setOnClickListener {
            val amount = binding.baseCurrency.editText?.text.toString().toInt()
            val from = binding.baseCurrencyList.text.toString()
            val to = binding.secondCurrencyList.text.toString()
            if (from.isNotEmpty()) {
                viewModel.convertTo(from, to, amount)
            }
        }
    }

}