package com.example.bmi

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bmi.R

class MainActivity : AppCompatActivity() {

    private lateinit var tvWeight: TextView
    private lateinit var tvHeight: TextView
    private lateinit var tvResult: TextView
    private lateinit var btnGo: Button
    private lateinit var keypadLayout: GridLayout

    private var currentField = "weight"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        tvWeight = findViewById(R.id.tvWeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvResult = findViewById(R.id.tvResult)
        btnGo = findViewById(R.id.btnGo)
        keypadLayout = findViewById(R.id.keypadLayout)

        setupKeypad()

        tvWeight.setOnClickListener {
            currentField = "weight"
        }

        tvHeight.setOnClickListener {
            currentField = "height"
        }

        btnGo.setOnClickListener {
            calculateBMI()
        }
    }

    private fun setupKeypad() {
        val buttons = listOf(
            "7", "8", "9", "AC",
            "4", "5", "6", "⌫",
            "1", "2", "3", ".",
            "", "0", "", ""
        )

        for (label in buttons) {
            val btn = Button(this).apply {
                text = label
                setTextColor(if (label == "GO" || label == "AC" || label == "⌫") 0xFFFFA500.toInt() else 0xFFFFFFFF.toInt())
                textSize = 20f
                setBackgroundColor(0xFF111111.toInt())
                setPadding(0, 20, 0, 20)
                visibility = if (label == "") View.INVISIBLE else View.VISIBLE
            }

            btn.setOnClickListener {
                onKeyPressed(label)
            }

            keypadLayout.addView(btn)
        }
    }

    private fun onKeyPressed(key: String) {
        val currentText = if (currentField == "weight") tvWeight.text.toString() else tvHeight.text.toString()

        when (key) {
            "AC" -> setFieldValue("0")
            "⌫" -> {
                val newValue = if (currentText.length > 1) currentText.dropLast(1) else "0"
                setFieldValue(newValue)
            }
            "." -> if (!currentText.contains(".")) setFieldValue(currentText + ".")
            else -> {
                val newText = if (currentText == "0") key else currentText + key
                setFieldValue(newText)
            }
        }
    }

    private fun setFieldValue(value: String) {
        if (currentField == "weight") {
            tvWeight.text = value
        } else {
            tvHeight.text = value
        }
    }

    private fun calculateBMI() {
        val weight = tvWeight.text.toString().toFloatOrNull() ?: return
        val heightCm = tvHeight.text.toString().toFloatOrNull() ?: return
        val heightM = heightCm / 100
        val bmi = weight / (heightM * heightM)

        val category = when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }

        tvResult.text = String.format("%.1f BMI - %s", bmi, category)
    }
}
