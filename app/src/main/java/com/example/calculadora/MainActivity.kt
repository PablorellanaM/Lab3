package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private var currentInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        setButtonListeners()
    }

    private fun setButtonListeners() {
        val buttonIds = listOf(
            R.id.btn1, R.id.btn2, R.id.btn3,
            //...
        )

        for (id in buttonIds) {
            val button = findViewById<Button>(id)
            button.setOnClickListener { v -> onButtonClick((v as Button).text.toString()) }
        }
    }

    private fun onButtonClick(text: String) {
        currentInput += text
        display.setText(currentInput)
    }

    private fun suma(n1: Int, n2: Int): Int {
        return n1 + n2
    }

    private fun resta(n1: Int, n2: Int): Int {
        return n1 - n2
    }

    private fun multiplicacion(n1: Int, n2: Int): Int {
        return n1 * n2
    }

    private fun division(n1: Int, n2: Int): Int {
        return if (n2 != 0) {
            n1 / n2
        } else {

            0
        }
    }
}
