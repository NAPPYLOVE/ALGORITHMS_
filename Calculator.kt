//Калькулятор
import kotlin.math.*
fun main() {


/*
        (a + b) * 912.76 - с / (d ^ e + e) + sin a ^ 2 - инфиксное
        =>
        stack[]   если приоритет текущего меньше чем послед операнд в стэке,
        то достаем все элементы из стэка которые меньше по приоритету и добавл в постфикс послед из стэка
        postfix[a, b, +, 912.76, *, c, d, e, ^, e, +, /, -, a, sin, 2, ^, +  ]
        если приоритет текущего выше чем послед операнд в стэке, то добавл в стэк
        =>
        [(a + b) * 912.76 - c / (d ^ e + e) + (sin a) ^ 2]
*/


    val expr = "-123.7 + (12.1 ^ 6 - 14) * 0.16 / (18.3 + 5.9)"
//    val expr = "( 345.66 - 4.45 ) / ( -43 - 2 ) * 33 + 3.09 / sin 2 ^ 1.3 * 8.2"
    val pattern = "-?\\d*\\.?\\d+|\\(|\\)|\\+|-|\\*|/|\\^|(sin)".toRegex()
    val list = pattern.findAll(expr).map { it.value }.toList()
    println(list)
    println(infixToPostfix(list))
    println(calc(infixToPostfix(list)))
}


fun infixToPostfix(list: List<String>): List<String> {
    val priority = mapOf (
        "+" to 1, "-" to 1,
        "*" to 2, "/" to 2,
        "^" to 3,
        "sin" to 4,
    )
    val stack = mutableListOf<String>()
    val postfix = mutableListOf<String>()
    for (str in list) {
        if (str.toDoubleOrNull() != null) {
            postfix.add(str)
        } else {
            when (str) {
                "(" -> stack.add(str)
                ")" -> {
                    while (stack.last() != "(") postfix.add(stack.removeLast())
                    stack.removeLast()
                }
                else -> {
                    while ((stack.isNotEmpty()) && (stack.last() != "(" )
                        && (priority.getValue(str) <= priority.getValue(stack.last()))) postfix.add(stack.removeLast())
                    stack.add(str)
                }
            }
        }
    }
    while (stack.isNotEmpty()) postfix.add(stack.removeLast())
    return postfix.toList()
}


fun calc(arrPostfix: List<String>): Double {
    val stack = mutableListOf<Double>()
    for (str in arrPostfix) {
        if (str.toDoubleOrNull() != null) stack.add(str.toDouble())
        when(str) {
            "+" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(a + b)
            }
            "-" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(b - a)
            }
            "*" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(a * b)
            }
            "/" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(b / a)
            }
            "^" -> {
                val a = stack.removeLast()
                val b = stack.removeLast()
                stack.add(b.pow(a))
            }
            "sin" -> {
                val a = stack.removeLast()
                stack.add(sin(a))
            }
        }
    }
    return stack[0]
}