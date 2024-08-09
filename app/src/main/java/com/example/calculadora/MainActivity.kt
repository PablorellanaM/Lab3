package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var operator: String? = null
    private var firstValue: Double? = null
    private var secondValue: Double? = null
    private var isOperatorPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals, R.id.btnClear, R.id.btnDot, R.id.btnBackspace
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { handleButtonPress(it as Button) }
        }
    }

    private fun handleButtonPress(button: Button) {
        when (button.id) {
            R.id.btnClear -> clear()
            R.id.btnBackspace -> backspace()
            R.id.btnEquals -> calculate()
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide -> operatorPressed(button.text.toString())
            else -> numberOrDotPressed(button.text.toString())
        }
    }

    private fun clear() {
        tvDisplay.text = "0"
        firstValue = null
        secondValue = null
        operator = null
        isOperatorPressed = false
    }

    private fun backspace() {
        val currentText = tvDisplay.text.toString()
        if (currentText.isNotEmpty()) {
            tvDisplay.text = currentText.dropLast(1)
            if (tvDisplay.text.isEmpty()) {
                tvDisplay.text = "0"
            }
        }
    }

    private fun operatorPressed(op: String) {
        firstValue = tvDisplay.text.toString().toDoubleOrNull()
        operator = op
        isOperatorPressed = true
    }

    private fun numberOrDotPressed(value: String) {
        if (isOperatorPressed) {
            tvDisplay.text = ""
            isOperatorPressed = false
        }

        if (tvDisplay.text.toString() == "0" && value != ".") {
            tvDisplay.text = value
        } else {
            tvDisplay.append(value)
        }
    }

    private fun calculate() {
        secondValue = tvDisplay.text.toString().toDoubleOrNull()

        val result = when (operator) {
            "+" -> firstValue?.plus(secondValue ?: 0.0)
            "-" -> firstValue?.minus(secondValue ?: 0.0)
            "*" -> firstValue?.times(secondValue ?: 1.0)
            "/" -> firstValue?.div(secondValue ?: 1.0)
            else -> secondValue
        }

        tvDisplay.text = result?.toString() ?: "Error"
        firstValue = result
        secondValue = null
        operator = null
    }
}