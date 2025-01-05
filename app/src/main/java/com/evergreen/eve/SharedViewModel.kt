package com.evergreen.eve

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _totalAmount = MutableLiveData<Long>()
    val totalAmount: LiveData<Long> get() = _totalAmount

    fun updateTotalAmount(amount: Long) {
        _totalAmount.value = amount
    }
}