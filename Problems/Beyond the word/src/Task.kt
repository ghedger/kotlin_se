// Posted from EduTools plugin
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val word = scanner.next().toLowerCase()
    oloop@ for (ac in 'a'..'z') {
        for (wc in word) {
            if (wc == ac) continue@oloop
        }
        print(ac)
    }
}