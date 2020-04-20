import java.util.*

fun main() {
    val letters = mutableMapOf<Int, Char>()
    val scanner = Scanner(System.`in`)
    var c = ' '
    var idx = 0
    var cont = true

    while (cont) {
        c = scanner.nextLine().first()
        letters[idx++] = c
        if ('z' == c) cont = false
    }

    if (idx > 3) { 
        print(letters[3])
    } else {
        println("null")
    }
}
