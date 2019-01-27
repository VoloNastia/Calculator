package com.example.anastasia.calculator

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var expression: TextView
    private lateinit var result: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expression = findViewById(R.id.textViewExpression)
        result = findViewById(R.id.textViewResult)

        buttonOne.setOnClickListener { appendToExpression("1") }
        buttonTwo.setOnClickListener { appendToExpression("2") }
        buttonThree.setOnClickListener { appendToExpression("3") }
        buttonFour.setOnClickListener { appendToExpression("4") }
        buttonFive.setOnClickListener { appendToExpression("5") }
        buttonSix.setOnClickListener { appendToExpression("6") }
        buttonSeven.setOnClickListener { appendToExpression("7") }
        buttonEight.setOnClickListener { appendToExpression("8") }
        buttonNine.setOnClickListener { appendToExpression("9") }
        buttonZero.setOnClickListener { appendToExpression("0") }

        buttonPlus.setOnClickListener { appendToExpression("+") }
        buttonMinus.setOnClickListener { appendToExpression("-") }
        buttonMult.setOnClickListener { appendToExpression("*") }
        buttonDiv.setOnClickListener { appendToExpression("/") }
        buttonBack.setOnClickListener {
            if (textViewExpression.text.toString().isNotEmpty()) {
                textViewExpression.text = textViewExpression.text.substring(0, textViewExpression.text.length - 1)
            }
        }
        buttonOpen.setOnClickListener { appendToExpression("(") }
        buttonClose.setOnClickListener { appendToExpression(")") }
        buttonClear.setOnClickListener {
            expression.text = ""
            result.text = ""
        }
        buttonDot.setOnClickListener { appendToExpression(".") }
        buttonEquals.setOnClickListener {
            recount()
        }
    }


    private fun appendToExpression(oper: String) {
        if (textViewExpression.text == "0") {
            textViewExpression.text = oper
        } else {
            textViewExpression.append(oper)
        }
    }
    private fun recount() {
        try {
            val expr = ExpressionBuilder(expression.text.toString()).build().evaluate()
            result.text = expr.toString()
        } catch (e: Exception) {
            Log.d("Exception", " message : " + e.message)
            result.text = getString(R.string.parceException)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("textViewExpression", expression.text.toString())
        outState.putString("textViewResult", result.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewExpression.text = savedInstanceState!!.getString("textViewExpression")
        textViewResult.text = savedInstanceState.getString("textViewResult")
    }


    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy: ")
    }

    companion object {
        private const val LOG_TAG = "MainActivity"
    }
}
