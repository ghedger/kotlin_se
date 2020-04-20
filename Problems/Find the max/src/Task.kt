// Posted from EduTools plugin
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val array = IntArray(n)

    // High water mark and index
    var hwm = Int.MIN_VALUE
    var hwmIdx = Int.MIN_VALUE
    for (i in 0..array.lastIndex) {
        array[i] = scanner.nextInt()
        if (array[i] > hwm) {
            hwmIdx = i
            hwm = array[i]
        }
    }
    println(hwmIdx)
}