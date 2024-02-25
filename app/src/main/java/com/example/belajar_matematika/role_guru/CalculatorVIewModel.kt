package com.example.belajar_matematika.role_guru

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

<<<<<<< HEAD
class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
=======



class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
    var calculationResult by mutableStateOf(" ")
>>>>>>> 1b7354278e08b53937d432c0736cda3b7f70a8c5

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

<<<<<<< HEAD
    private fun enterOperation(operation: CalculatorOperation) {
=======
    fun enterOperation(operation: CalculatorOperation) {
>>>>>>> 1b7354278e08b53937d432c0736cda3b7f70a8c5
        if(state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

<<<<<<< HEAD
    private fun calculate() {
=======
    fun calculate() {
>>>>>>> 1b7354278e08b53937d432c0736cda3b7f70a8c5
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toInt().toString().take(15),
                number2 = "",
                operation = null
            )
<<<<<<< HEAD
        }
    }

    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
=======
            calculationResult = result.toString()
        }
    }

    fun getResult(): String {
        return calculationResult
    }

    fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    fun enterDecimal() {
>>>>>>> 1b7354278e08b53937d432c0736cda3b7f70a8c5
        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

<<<<<<< HEAD
    private fun enterNumber(number: Int) {
=======
    fun enterNumber(number: Int) {
>>>>>>> 1b7354278e08b53937d432c0736cda3b7f70a8c5
        if(state.operation == null) {
            if(state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}