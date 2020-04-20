import java.lang.Character.isDigit
import java.lang.NumberFormatException
import java.util.*

fun parseCardNumber(cardNumber: String): Long {
    val scanner = Scanner(System.`in`)
    val filteredNumber = cardNumber.filter({ c: Char -> isDigit(c) })
    if (16 == filteredNumber.length) {
        var spacesTot = 0
        for (c in cardNumber) {
            if (' ' == c) spacesTot++
        }
        if (3 != spacesTot) {
            throw NumberFormatException()
        }

        val number = filteredNumber.toLong()
        return number
    } else {
        throw NumberFormatException()
    }
}