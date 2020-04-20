import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val dividend = scanner.nextInt()
    val divisor = scanner.nextInt()
    if (divisor != 0) {
        val quotient = dividend / divisor
        println(quotient)
    } else {
        println("Division by zero, please fix the second argument!")
    }
}