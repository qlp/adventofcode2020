package nl.qlp.aoc20.day18

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = readLinesFromInput().map { Parser(it).solve() }.sum()

    class Parser(val string: String) {
        fun solve(): Long {
            val clean = string.replace(" ", "")

            val tokens = mutableListOf<String>()
            val buffer = StringBuilder()

            clean.forEachIndexed { index, c ->
                if (buffer.isEmpty()) {
                    when (c) {
                        '(' -> buffer.append(c)
                        else -> tokens.add(c.toString())
                    }
                } else {
                    buffer.append(c)

                    if (buffer.count { it == '(' } == buffer.count { it == ')'}) {
                        tokens.add(Parser(buffer.substring(1, buffer.length - 1)).solve().toString())
                        buffer.clear()
                    }
                }
            }

            var result = tokens[0].toLong()

            repeat((tokens.size - 1) / 2) {
                val operator = tokens[it * 2 + 1]
                val value = tokens[it * 2 + 2].toLong()

                when (operator) {
                    "+" -> result += value
                    "*" -> result *= value
                    else -> throw RuntimeException("Unsupported operator: $operator")
                }
            }

            return result
        }
    }
}