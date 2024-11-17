package com.example.basil


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class LoanViewModel : ViewModel() {

    private val _monthlyPayment = MutableLiveData(0.0)
    val monthlyPayment: LiveData<Double> get() = _monthlyPayment

    fun calculateMonthlyPayment(principal: Double, rate: Double, years: Int) {
        val monthlyRate = rate / 100 / 12
        val totalPayments = years * 12

        // Calculate monthly payment using loan formula
        _monthlyPayment.value = if (monthlyRate != 0.0) {
            (principal * monthlyRate) / (1 - (1 + monthlyRate).pow(-totalPayments))
        } else {
            principal / totalPayments
        }
    }
}
