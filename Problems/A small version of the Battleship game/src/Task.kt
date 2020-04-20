// Posted from EduTools plugin
import java.util.Scanner

const val SHIPTOT = 3
const val COLUMNTOT = 5
const val ROWTOT = 5

fun main() {
    val xcoords = IntArray(SHIPTOT + 1) { 0 } // Supposedly this inits entire array
    val ycoords = IntArray(SHIPTOT + 1) { 0 }

    // Gather ship coordinates
    val input = Scanner(System.`in`)
    for (i in 0 until SHIPTOT) {
        xcoords[i] = input.nextInt()
        ycoords[i] = input.nextInt()
    }

    // Build occupancy
    val columnsOccupied = BooleanArray(COLUMNTOT + 1) { false }
    val rowsOccupied = BooleanArray(ROWTOT + 1) { false }
    for (i in 0 until SHIPTOT) {
        // Bounds check
        if (xcoords[i] in 1..COLUMNTOT) {
            columnsOccupied[xcoords[i]] = true
        }
    }
    for (i in 0 until SHIPTOT) {
        // Bounds check
        if (ycoords[i] in 1..ROWTOT) {
            rowsOccupied[ycoords[i]] = true
        }
    }

    // Show available spots
    var first = true
    for (i in 1..COLUMNTOT) {
        if (!columnsOccupied[i]) {
            if (!first) print(" ")
            first = false
            print("$i")
        }
    }
    println()
    first = true
    for (i in 1..ROWTOT) {
        if (!rowsOccupied[i]) {
            if (!first) print(" ")
            print("$i")
            first = false
        }
    }
    println()
}