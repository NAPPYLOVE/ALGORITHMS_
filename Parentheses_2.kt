//Задача про правильную расстановку трех видов скобок
fun main() {

    val stack = mutableListOf<String>()
    val ss = readLine()
    var right: Boolean = true

    for (i in ss.orEmpty()){
        if (i in "[{(") {
            stack.add("$i")
        }

        if (i in "]})") {
            if (stack.isEmpty()){
                right = false
                break
            }

            var opnbr = stack.removeLast()
            if (((opnbr == "(" && i in ")") || (opnbr == "[" && i in "]") || (opnbr == "{" && i in "}")) == false)
            right = false
        }
    }
    if (right == true && stack.isEmpty())
        println("RIGHT")
    else
        println("ERROR")
}