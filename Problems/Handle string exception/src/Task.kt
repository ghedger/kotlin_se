fun main() {
    try {
        val index = readLine()!!.toInt()
        val word = readLine()!!
        println(word[index])
    } catch (exception: Exception) {
        println("There isn't such an element in the given string, please fix the index!")
    }
}
