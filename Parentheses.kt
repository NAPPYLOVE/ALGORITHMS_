//Задача про правильную расстановку трех видов скобок
fun main() {

    val stack = mutableListOf<String>()
    val ss = readLine()
    var rght: Boolean = true

    for (i in ss.orEmpty()){
        if (i in "[{(") {
            stack.add("$i")
        }

        if (i in "]})") {
            if (stack.isEmpty()){
                rght = false
                break
            }

            var opnbr = stack.removeLast()
            if (opnbr == "(" && i in ")")
            if (opnbr == "[" && i in "]")
            if (opnbr == "{" && i in "}")
            rght = false
        }
    }

    if (rght == true && stack.isEmpty()) println("RIGHT") else println("ERROR")

}