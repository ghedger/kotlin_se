// Posted from EduTools plugin
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var sorted = true
    val n = scanner.nextInt()
    var x: Int
    var previous = Int.MIN_VALUE
    for (i in 0 until n) {
        x = scanner.nextInt()
        if (x < previous) {
            sorted = false
            break
        }
        previous = x
    }
    if (sorted) {
        println("YES")
    } else {
        println("NO")
    }
}