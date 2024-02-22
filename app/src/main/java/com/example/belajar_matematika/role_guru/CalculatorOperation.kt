package com.example.belajar_matematika.role_guru

sealed class CalculatorOperation ( val symbol : String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("x")
    object Divide : CalculatorOperation("/")
}