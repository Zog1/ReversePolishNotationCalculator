package com.example.kalkulator

import android.util.Log

 class OperationsHelper {
    companion object{
        fun RPN_Calculate(expr: String): Double? {
           if (!expr.isEmpty()) {
               var number_stack = mutableListOf<Double>()
               val tokens = expr.split(' ').filter { it != "" }
               for (token in tokens) {
                   val d = token.toDoubleOrNull()
                   if(d != null){
                       number_stack.add(d)
                   }else if((token.length > 1) || token !in "+-*/^"){
                       return null
                   }else if(number_stack.size < 2){
                       return null
                   }else{
                      val n1 = number_stack.removeAt(number_stack.lastIndex)
                       val n2 = number_stack.removeAt(number_stack.lastIndex)
                       number_stack.add(when(token){
                           "+" -> n2 + n1
                           "-" -> n2 - n1
                           "*" -> n2 * n1
                           "/" -> n2 / n1
                           else -> Math.pow(n2, n1)
                       })
                   }
               }
               return number_stack.last()

           }
               return null
       }
    }
}