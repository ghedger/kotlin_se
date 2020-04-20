// Posted from EduTools plugin
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val array = IntArray(n)

    // High water mark and index
    var state = 0
    var tripleCount = 0
    var previous = Int.MIN_VALUE
    for (i in 0..array.lastIndex) {
        array[i] = scanner.nextInt()
        if (1 == array[i] - previous) {
            state++
            if (2 == state) {
                tripleCount++
                state--
            }
        } else {
            state = 0
        }
        previous = array[i]
    }
    println(tripleCount)
}