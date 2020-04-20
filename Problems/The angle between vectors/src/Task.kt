// Posted from EduTools plugin
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val x1 = scanner.nextDouble()
    val y1 = scanner.nextDouble()
    val x2 = scanner.nextDouble()
    val y2 = scanner.nextDouble()
    val umag = Math.sqrt(x1 * x1 + y1 * y1)
    val vmag = Math.sqrt(x2 * x2 + y2 * y2)
    val dp = x1 * x2 + y1 * y2
    val costheta = dp / (umag * vmag)
    val angle = Math.acos(costheta) * 180 / Math.PI
    println(angle)
}