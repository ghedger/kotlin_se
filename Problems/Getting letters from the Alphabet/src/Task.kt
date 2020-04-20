// Posted from EduTools plugin
import java.util.Scanner

fun printProceedingLetters(letter: Char) {
    for (c in 'a'..'z') {
        if (c == letter) return
        print(c)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val letter = scanner.next().first().toLowerCase()
    if (letter in 'a'..'z') {
        printProceedingLetters(letter)
    }
}