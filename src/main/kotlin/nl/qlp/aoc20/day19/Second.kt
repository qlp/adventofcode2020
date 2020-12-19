package nl.qlp.aoc20.day19

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(Second().run())
}

class Second {
    fun run(): Int {
        val input = readFromInput().split("\n\n")

        val rulesByIndex = input[0].split("\n").map { ruleLine ->
            ruleLine.split(":").let { it[0].toInt() to it[1].parseRule(it[0]) }
        }

        val rules = arrayOfNulls<Rule>(rulesByIndex.maxOf { it.first } + 1)

        rulesByIndex.forEach { rules[it.first] = it.second }

        rules[8] = "42 | 42 8".parseRule("8")
        rules[11] = "42 31 | 42 11 31".parseRule("11")

        val ruleBook = RuleBook(rules.asList())
        val messages = input[1].split("\n")

        ruleBook.rules.forEachIndexed { index, rule ->
            println("[$index] => $rule")
        }

        return messages.filter { ruleBook.isValid(it) }.onEach { println(it) }.size
    }

    data class RuleBook(val rules: List<Rule?>) {
        fun isValid(message: String) = rules[0]!!.isValid(message, 0, this, listOf()).let { it.valid && it.consumed.any { consumed -> consumed == message.length }}
    }

    data class ParseResult(val valid: Boolean, val consumed: Set<Int> = emptySet())

    abstract class Rule(val id: String) {
        abstract fun isValid(message: String, charAt: Int, ruleBook: RuleBook, stack: List<Call>): ParseResult
    }

    class CharRule(id: String, val char: Char) : Rule(id) {
        override fun toString() = "{$id: '$char'}"
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook, stack: List<Call>): ParseResult {
            return when {
                message.length > charAt -> if (message[charAt] == char) ParseResult(true, setOf(1)) else ParseResult(false)
                else -> ParseResult(false)
            }
        }
    }

    class AnyRule(id: String, val rules: List<Rule>) : Rule(id) {
        override fun toString() = "{$id: ${rules.joinToString(separator = " | ")}}"
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook, stack: List<Call>): ParseResult {
            return rules
                .map { it.isValid(message, charAt, ruleBook, stack.toMutableList().apply { add(Call(id, charAt)) }.toList()) }
                .filter { it.valid }
                .flatMap { it.consumed }
                .let {
                    ParseResult(it.isNotEmpty(), it.toSet())
                }
        }
    }

    class SequenceRule(id: String, val rules: List<Rule>) : Rule(id) {
        override fun toString() = "{$id: ${rules.joinToString(separator = " ")}}"
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook, stack: List<Call>): ParseResult {
            var result = setOf(0)

            rules.forEach { rule ->
                val newConsumed = mutableSetOf<Int>()
                result.toSet().forEach { consumed ->
                    val parseResult = rule.isValid(message, charAt + consumed, ruleBook, stack.toMutableList().apply { add(Call(id, charAt)) }.toList())

                    if (parseResult.valid) {
                        newConsumed.addAll(parseResult.consumed.map { consumed + it })
                    }
                }
                result = newConsumed
            }

            return ParseResult(result.isNotEmpty(), result)
        }
    }

    class ReferenceRule(id: String, val index: Int) : Rule(id) {
        override fun toString() = "{$id: $index}"

        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook, stack: List<Call>): ParseResult {
            return ruleBook.rules[index]!!.isValid(message, charAt, ruleBook, stack.toMutableList().apply { add(Call(id, charAt)) }.toList())
        }
    }

    data class Call(val id: String, val charAt: Int) {
        override fun toString() = "$id/$charAt"
    }

    private fun String.parseRule(parent: String): Rule {
        val ruleText = trim()

        return when {
            ruleText.startsWith("\"") -> CharRule(parent, ruleText[1])
            ruleText.contains("|") -> AnyRule(parent, ruleText.split("|").mapIndexed { index, s -> s.parseRule("$parent.$index") })
            ruleText.contains(" ") -> SequenceRule(parent, ruleText.split(" ").mapIndexed { index, s -> s.parseRule("$parent.$index") })
            else -> ReferenceRule(parent, ruleText.toInt())
        }
    }
}