package com.example.kalkulator

import android.media.VolumeShaper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    var digit_on_screen = StringBuilder()


    var numberStack = mutableListOf<String>()
    var operationStack = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeButtons()
        numberStack.add("")
        operationStack.add("")
    }

    private fun initializeButtons() {
        functionalbuttons()
        operationButtons()
        numericalButtons()
    }

    private fun numericalButtons(){
        btn_zero.setOnClickListener{
            appendToDigitOnScreen("0")
        }
        btn_one.setOnClickListener{
            appendToDigitOnScreen("1")
        }
        btn_two.setOnClickListener{
            appendToDigitOnScreen("2")
        }
        btn_three.setOnClickListener{
            appendToDigitOnScreen("3")
        }
        btn_four.setOnClickListener{
            appendToDigitOnScreen("4")
        }
        btn_five.setOnClickListener{
            appendToDigitOnScreen("5")
        }
        btn_six.setOnClickListener{
            appendToDigitOnScreen("6")
        }
        btn_seven.setOnClickListener{
            appendToDigitOnScreen("7")
        }
        btn_eight.setOnClickListener{
            appendToDigitOnScreen("8")
        }
        btn_nine.setOnClickListener{
            appendToDigitOnScreen("9")
        }
        btn_dot.setOnClickListener{
            appendToDigitOnScreen(".")
        }
    }

    private fun  appendToDigitOnScreen(digit: String){
        digit_on_screen.append(digit)
        result.text = digit_on_screen.toString()
    }

    private fun operationButtons(){
        btn_plus.setOnClickListener{
            digit_on_screen.clear()
            numberStack.add(result.text.toString()+" ")
            operationStack.add(0," + ")
            if (isOperation())
                addToExpression()
            result.text = "0"
        }
        btn_minus.setOnClickListener{
            digit_on_screen.clear()
            numberStack.add(result.text.toString()+" ")
            operationStack.add(0," - ")
            if (isOperation())
                addToExpression()
            result.text = "0"
        }
        btn_multiple.setOnClickListener{
            numberStack.add(result.text.toString()+" ")
            if (isOperation())
                addToExpression()
            operationStack.add(0," * ")
            digit_on_screen.clear()
            result.text = "0"
        }
        btn_divide.setOnClickListener{
            digit_on_screen.clear()
            numberStack.add(result.text.toString()+" ")
            if (isOperation())
                addToExpression()
            operationStack.add(0," / ")
            result.text = "0"
        }
        btn_sqrt.setOnClickListener{
            digit_on_screen.clear()
            result.text = sqrt(result.text.toString().toDouble()).toString()
        }
        btn_binary.setOnClickListener{

            digit_on_screen.clear()
            result.text =  floor(result.text.toString().toDouble()).toInt().to32bitString()
        }
        btn_power.setOnClickListener{
            digit_on_screen.clear()
            numberStack.add(result.text.toString()+" ")
            if (isOperation())
                addToExpression()
            result.text = "0"
            operationStack.add(0, " ^ ")
        }
    }

    private fun isOperation():Boolean{
      return operationStack.first() in "*/^"
  }

    private fun addToExpression(){
        for (o in operationStack){
            numberStack.add(o)
        }
        operationStack.clear()
        operationStack.add("")
    }

    private fun functionalbuttons(){
        btn_delete.setOnClickListener{
            digit_on_screen.clear()
            numberStack.clear()
            operationStack.clear()
            operationStack.add("")
            numberStack.add("")
            result.text = "0"
        }
        btn_equal.setOnClickListener{
            numberStack.add(result.text.toString()+" ")
            addToExpression()
         performMathOperation()
        }
    }

    private fun Int.to32bitString(): String =
        Integer.toBinaryString(this).padStart(Int.SIZE_BITS, '0')

    private fun performMathOperation(){
        var rpn_exp = ""
        for (n in numberStack)
            rpn_exp += n
        result.text = OperationsHelper.RPN_Calculate(rpn_exp).toString()
        digit_on_screen.clear()

    }
}
