//import java.util.*

fun main() {
    val letter = readLine()!!
    //val scanner = Scanner(System.`in`)
    if (letter.isNotEmpty()) {
        val myMap: MutableMap<Char, Int> = mutableMapOf('a' to 1, 'e' to 5, 'i' to 9, 'o' to 15, 'u' to 21)
        if (myMap.contains(letter.first().toLowerCase())) {
            println(myMap[letter.first().toLowerCase()])
        } else {
            println(0)
        }
    }
}