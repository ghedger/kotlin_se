package search

import java.io.File
import java.util.Scanner

enum class USERCHOICE {
    EXIT,
    FIND,
    PRINTALL
}

enum class STRATEGY {
    ANY,
    ALL,
    NONE
}

class SearchEngine
{
    private var dataItemsTot: Int = 0
    private var database: MutableList<String> = mutableListOf()
    private val scanner: Scanner = Scanner(System.`in`)
    private var wordMap: MutableMap<String, MutableList<Int>> = mutableMapOf()

    // private var searchesRemaining: Int = 0
    var choice: USERCHOICE = USERCHOICE.EXIT
    private var strategy: STRATEGY = STRATEGY.ANY

    // addRecord
    // Add a single line of text ("record") to the database
    // Entry: @record  line of text
    fun addRecord(record: String) {
        database.add(dataItemsTot++, record)
    }

    // getWordIndex
    // Get list of record/line indices at which
    // a given word appears in the database, or empty list if none.
    // Entry @word  word to seek, no spaces
    // Exit: list of indices at which word appears
    private fun getWordIndex(word: String): MutableList<Int> {
        if (wordMap.containsKey(word)) {
            // !! means:
            // "Don't get your knickers in a wad: Result won't be null!"
            return wordMap[word]!!
        }
        return emptyList<Int>().toMutableList()
    }

    // indexWord
    // Index an individual word, case insensitive, to a line #, accounting
    // for possibility word is already present.  In the latter case,
    // add the line index as another reverse-index of where, linewise,
    // the word occurs in the database.
    private fun indexWord(word: String, index: Int) {
        val wordReverseIndex = getWordIndex(word.toUpperCase())
        if (wordReverseIndex.isEmpty()) {
            // This word does not yet appear; let's add it
            wordMap[word.toUpperCase()] = mutableListOf(index)
        } else {
            wordMap[word.toUpperCase()]!!.add(index)
        }
    }

    // indexWords
    // Call after all records are added
    fun indexWords() {
        var lineWords: List<String>
        // Iterate through all words on each line;
        // add uppercased word to canonical word dictionary and
        // reverse-index to line
        for ((lineIdx, line) in database.withIndex()) {
            lineWords = line.split(' ', ignoreCase = true)
            for (word in lineWords) {
                indexWord(word.toUpperCase(), lineIdx)
            }
        }
    }

    // NextSearch
    // Exit: true == result found
    private fun nextSearch(term: String): MutableList<Int> {
        val searchTerm = term.toUpperCase()   // scanner.next().toUpperCase()
        return getWordIndex(searchTerm)
    }

    private fun nextMultiSearch(terms: List<String>): MutableList<Int> {
        when (strategy) {
            STRATEGY.ALL -> {
                val resultSet: MutableList<Int> = nextSearch(terms[0])
                var nextResultSet: MutableList<Int>
                for (term in terms) {
                    nextResultSet = nextSearch(term)
                    for (result in resultSet) {
                        if (!nextResultSet.contains(result)) {
                            resultSet.remove(result)
                        }
                    }
                }
                return resultSet
            }
            STRATEGY.ANY -> {
                val resultSet: MutableList<Int> = nextSearch(terms[0])
                var nextResultSet: MutableList<Int>
                for (term in terms) {
                    nextResultSet = nextSearch(term)
                    for (result in nextResultSet) {
                        if (!resultSet.contains(result)) {
                            resultSet.add(result)
                        }
                    }
                }
                return resultSet
            }
            STRATEGY.NONE -> {
                // First, get an "any" boolean OR merged list
                val resultSet: MutableList<Int> = nextSearch(terms[0])
                var nextResultSet: MutableList<Int>
                for (term in terms) {
                    nextResultSet = nextSearch(term)
                    for (result in nextResultSet) {
                        if (!resultSet.contains(result)) {
                            resultSet.add(result)
                        }
                    }
                }

                // Now get its inverse
                val finalResultSet: MutableList<Int> = mutableListOf()
                var frsIdx = 0
                for (i in 0 until dataItemsTot) {
                    if (!resultSet.contains(i)) {
                        finalResultSet.add(frsIdx++, i)
                    }
                }
                return finalResultSet
            }
        }
        return mutableListOf()
    }

    // showStrategyMenu
    private fun showStrategyMenu() {
        println("Select a matching strategy: ALL, ANY, NONE")
    }

    // showChoiceMenu
    private fun showChoiceMenu() {
        println("=== Menu ===")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")
    }

    fun getMatchingStrategy(): Boolean {
        var result = true
        showStrategyMenu()
        when (scanner.next()) {
            "ANY" -> {
                strategy = STRATEGY.ANY
            }
            "ALL" -> {
                strategy = STRATEGY.ALL
            }
            "NONE" -> {
                strategy = STRATEGY.NONE
            }
            else -> {
                result = false
            }
        }
        return result
    }

    // getChoice
    fun getUserChoice(): Boolean {
        var result = true           // assume success
        showChoiceMenu()
        when (scanner.nextInt()) {
            0 -> {
                choice = USERCHOICE.EXIT
            }
            1 -> {
                choice = USERCHOICE.FIND
            }
            2 -> {
                choice = USERCHOICE.PRINTALL
            }
            else -> {
                result = false
            }
        }
        return result
    }

    fun printAll() {
        println ("=== List of people ===")
        val it: MutableListIterator<String> = database.listIterator()
        while (it.hasNext()) {
            val e = it.next()
            println(e)
        }
    }

    fun find() {
        println("Enter a name or email to search all suitable people.")
        var term = scanner.nextLine()
        if (term.isEmpty()) term = scanner.nextLine()
        val terms: List<String> = term.split(' ')
        val resultSet = nextMultiSearch(terms)
        if (resultSet.isEmpty()) {
            println("No matching people found.")
        } else {
            println("${resultSet.size} persons found:")
            for (index in resultSet) {
                println(database[index])
            }
        }
    }
}

fun main(args: Array<String>) {
    var readFromFile = false
    var fileName = ""
    if (args.isNotEmpty()) {
        if (args[0] == "--data") {
            readFromFile = true
            fileName = args[1]
        }
    }

    if (readFromFile) {
        val se = SearchEngine()

        val myFile = File(fileName)
        val lines = myFile.readLines()
        for (line in lines) {
            se.addRecord(line)
        }

        // Tie all words in database to record indices at which
        // they occur.
        se.indexWords()

        var exit = false
        // Main program flow
        while (!exit) {
            while (!se.getUserChoice()) {
                println("Incorrect option! Try again")
            }
            when (se.choice) {
                USERCHOICE.EXIT -> {
                    exit = true
                }
                USERCHOICE.PRINTALL -> {
                    se.printAll()
                }
                USERCHOICE.FIND -> {
                    while (!se.getMatchingStrategy()) {
                        println("Incorrect option! Try again")
                    }
                    se.find()
                }
            }
        }
    }
}