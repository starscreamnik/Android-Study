package com.example.lab2

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.pow
import java.lang.Math.round


class MainActivity : AppCompatActivity() {
    private fun round(num: Double, precision: Double): Double {
        return round(num * pow(10.0, precision)) / pow(10.0, precision)
    }
    @SuppressLint("SetTextI18n")    //for CAPS?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlus.setOnClickListener { v ->
            when {
                input1.text.isEmpty() -> Snackbar.make(v, "Заполни поле слева", Snackbar.LENGTH_LONG).show()
                input2.text.isEmpty() -> Snackbar.make(v, "Заполни поле справа", Snackbar.LENGTH_LONG).show()
                else -> {
                    val x = (input1.text.toString()).toDouble()
                    val y = (input2.text.toString()).toDouble()
                    resultText.text = round(x + y, 2.0).toString()
                }
            }
        }
        btnSub.setOnClickListener{ v ->
            when {
                input1.text.isEmpty() -> Snackbar.make(v, "Заполни поле слева", Snackbar.LENGTH_LONG).show()
                input2.text.isEmpty() -> Snackbar.make(v, "Заполни поле справа", Snackbar.LENGTH_LONG).show()
                else -> {
                    val x = (input1.text.toString()).toDouble()
                    val y = (input2.text.toString()).toDouble()
                    resultText.text = round(x - y, 2.0).toString()
                }
            }
        }
        btnMul.setOnClickListener{v ->
            when {
                input1.text.isEmpty() -> Snackbar.make(v, "Заполни поле слева", Snackbar.LENGTH_LONG).show()
                input2.text.isEmpty() -> Snackbar.make(v, "Заполни поле справа", Snackbar.LENGTH_LONG).show()
                else -> {
                    val x = (input1.text.toString()).toDouble()
                    val y = (input2.text.toString()).toDouble()
                    resultText.text = round(x * y, 2.0).toString()
                }
            }
        }
        btnDiv.setOnClickListener{v ->
            when {
                input1.text.isEmpty() -> Snackbar.make(v, "Заполни поле слева", Snackbar.LENGTH_LONG).show()
                input2.text.isEmpty() -> Snackbar.make(v, "Заполни поле справа", Snackbar.LENGTH_LONG).show()
                input2.text.toString() == "0" -> if(input1.text.toString() == "0")
                    resultText.text = "NaN"
                else resultText.text = "INF"
                else -> {
                    val x = (input1.text.toString()).toDouble()
                    val y = (input2.text.toString()).toDouble()
                    resultText.text = round(x / y, 2.0).toString()
                }
            }
        }
    }
}

