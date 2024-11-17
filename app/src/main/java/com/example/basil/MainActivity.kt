package com.example.basil

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.basil.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the calculate button
        binding.btnCalculate.setOnClickListener {
            // Get the input values
            val principal = binding.editTextPrincipal.text.toString().toDoubleOrNull() ?: 0.0
            val rate = binding.editTextRate.text.toString().toDoubleOrNull() ?: 0.0
            val years = binding.editTextYears.text.toString().toIntOrNull() ?: 0

            // Validate inputs (optional, but recommended)
            if (principal <= 0 || rate <= 0 || years <= 0) {
                binding.textViewResult.text = "Please enter valid values."
                return@setOnClickListener
            }

            // Calculate the monthly payment
            val monthlyPayment = calculateMonthlyPayment(principal, rate, years)

            // Display the result
            binding.textViewResult.text = "Monthly Payment: $${String.format("%.2f", monthlyPayment)}"
        }
    }

    // Function to calculate monthly payment
    private fun calculateMonthlyPayment(principal: Double, rate: Double, years: Int): Double {
        val monthlyRate = rate / 100 / 12
        val numberOfPayments = years * 12

        if (principal <= 0 || rate <= 0 || years <= 0) return 0.0

        // Formula to calculate monthly payment
        val numerator = monthlyRate * (1 + monthlyRate).pow(numberOfPayments.toDouble())
        val denominator = (1 + monthlyRate).pow(numberOfPayments.toDouble()) - 1

        return principal * numerator / denominator
    }
}
