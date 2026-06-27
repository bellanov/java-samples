package com.example.patterns

/**
 * Simple calculator demonstrating Kotlin patterns.
 * Shows functional programming, sealed classes, and when expressions.
 */
sealed class CalculationResult {
    data class Success(val value: Double) : CalculationResult()
    data class Error(val message: String) : CalculationResult()
}

class Calculator {
    
    fun add(a: Double, b: Double): CalculationResult = 
        CalculationResult.Success(a + b)
    
    fun subtract(a: Double, b: Double): CalculationResult = 
        CalculationResult.Success(a - b)
    
    fun multiply(a: Double, b: Double): CalculationResult = 
        CalculationResult.Success(a * b)
    
    fun divide(a: Double, b: Double): CalculationResult = 
        if (b == 0.0) {
            CalculationResult.Error("Division by zero")
        } else {
            CalculationResult.Success(a / b)
        }
    
    fun calculate(operation: String, a: Double, b: Double): CalculationResult =
        when (operation.lowercase()) {
            "add" -> add(a, b)
            "subtract" -> subtract(a, b)
            "multiply" -> multiply(a, b)
            "divide" -> divide(a, b)
            else -> CalculationResult.Error("Unknown operation: $operation")
        }
}

/**
 * Kotlin extension function demonstrating idiomatic Kotlin.
 */
fun CalculationResult.getValueOrNull(): Double? = when (this) {
    is CalculationResult.Success -> this.value
    is CalculationResult.Error -> null
}

/**
 * Extension function to format calculation results.
 */
fun CalculationResult.format(): String = when (this) {
    is CalculationResult.Success -> "Result: ${this.value}"
    is CalculationResult.Error -> "Error: ${this.message}"
}
