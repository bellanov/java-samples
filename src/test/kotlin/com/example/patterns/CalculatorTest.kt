package com.example.patterns

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

import org.assertj.core.api.Assertions.*

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private lateinit var calculator: Calculator
    
    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }
    
    @Test
    @DisplayName("Should add two numbers")
    fun testAdd() {
        val result = calculator.add(5.0, 3.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Success::class.java)
        val success = result as CalculationResult.Success
        assertThat(success.value).isEqualTo(8.0)
    }
    
    @Test
    @DisplayName("Should subtract two numbers")
    fun testSubtract() {
        val result = calculator.subtract(10.0, 3.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Success::class.java)
        val success = result as CalculationResult.Success
        assertThat(success.value).isEqualTo(7.0)
    }
    
    @Test
    @DisplayName("Should multiply two numbers")
    fun testMultiply() {
        val result = calculator.multiply(4.0, 5.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Success::class.java)
        val success = result as CalculationResult.Success
        assertThat(success.value).isEqualTo(20.0)
    }
    
    @Test
    @DisplayName("Should divide two numbers")
    fun testDivide() {
        val result = calculator.divide(10.0, 2.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Success::class.java)
        val success = result as CalculationResult.Success
        assertThat(success.value).isEqualTo(5.0)
    }
    
    @Test
    @DisplayName("Should return error when dividing by zero")
    fun testDivideByZero() {
        val result = calculator.divide(10.0, 0.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Error::class.java)
        val error = result as CalculationResult.Error
        assertThat(error.message).contains("Division by zero")
    }
    
    @Test
    @DisplayName("Should calculate using operation string")
    fun testCalculateWithOperationString() {
        val result = calculator.calculate("add", 5.0, 3.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Success::class.java)
        val success = result as CalculationResult.Success
        assertThat(success.value).isEqualTo(8.0)
    }
    
    @Test
    @DisplayName("Should return error for unknown operation")
    fun testCalculateUnknownOperation() {
        val result = calculator.calculate("power", 5.0, 3.0)
        
        assertThat(result).isInstanceOf(CalculationResult.Error::class.java)
        val error = result as CalculationResult.Error
        assertThat(error.message).contains("Unknown operation")
    }
    
    @Test
    @DisplayName("Should format success result")
    fun testFormatSuccessResult() {
        val result = calculator.add(5.0, 3.0)
        val formatted = result.format()
        
        assertThat(formatted).isEqualTo("Result: 8.0")
    }
    
    @Test
    @DisplayName("Should format error result")
    fun testFormatErrorResult() {
        val result = calculator.divide(10.0, 0.0)
        val formatted = result.format()
        
        assertThat(formatted).contains("Error", "Division by zero")
    }
    
    @Test
    @DisplayName("Should get value or null from result")
    fun testGetValueOrNull() {
        val successResult = calculator.add(5.0, 3.0)
        val errorResult = calculator.divide(10.0, 0.0)
        
        assertThat(successResult.getValueOrNull()).isEqualTo(8.0)
        assertThat(errorResult.getValueOrNull()).isNull()
    }
}
