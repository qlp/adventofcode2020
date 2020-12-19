package nl.qlp.aoc20.day19

import nl.qlp.aoc20.readFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run(): Int {
        val input = readFromInput().split("\n\n")

        val rulesByIndex = input[0].split("\n").map { ruleLine ->
            ruleLine.split(":").let { it[0].toInt() to it[1].parseRule() }
        }

        val rules = arrayOfNulls<Rule>(rulesByIndex.maxOf { it.first } + 1)

        rulesByIndex.forEach { rules[it.first] = it.second }

        val ruleBook = RuleBook(rules.asList())
        val messages = input[1].split("\n")

        ruleBook.rules.forEachIndexed { index, rule ->
            println("[$index] => $rule")
        }

        return messages.filter { ruleBook.isValid(it) }.onEach { println(it) }.size
    }

    data class RuleBook(val rules: List<Rule?>) {
        fun isValid(message: String) = rules[0]!!.isValid(message, 0, this).let { it.valid && it.consumed == message.length }
    }

    data class ParseResult(val valid: Boolean, val consumed: Int = 0)

    interface Rule {
        fun isValid(message: String, charAt: Int, ruleBook: RuleBook): ParseResult
    }

    data class CharRule(val char: Char) : Rule {
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook) = when {
            message.length > charAt -> if (message[charAt] == char) ParseResult(true, 1) else ParseResult(false)
            else -> ParseResult(false)
        }
    }

    data class AnyRule(val rules: List<Rule>) : Rule {
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook) = rules
            .map { it.isValid(message, charAt, ruleBook) }
            .firstOrNull { it.valid }
            ?: ParseResult(false)
        }

    data class SequenceRule(val rules: List<Rule>) : Rule {
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook): ParseResult {
            var consumed = 0

            rules.forEach { rule ->
                val parseResult = rule.isValid(message, charAt + consumed, ruleBook)

                if (!parseResult.valid) {
                    return ParseResult(false)
                } else {
                    consumed += parseResult.consumed.also { println("consumed: $it") }
                }
            }

            return ParseResult(true, consumed)
        }
    }
    data class RuleReference(val index: Int) : Rule {
        override fun isValid(message: String, charAt: Int, ruleBook: RuleBook): ParseResult {
            println("$index $message $charAt")

            return ruleBook.rules[index]!!.isValid(message, charAt, ruleBook)
        }
    }

    private fun String.parseRule(): Rule {
        val ruleText = trim()

        return when {
            ruleText.startsWith("\"") -> CharRule(ruleText[1])
            ruleText.contains("|") -> AnyRule(ruleText.split("|").map { it.parseRule() })
            ruleText.contains(" ") -> SequenceRule(ruleText.split(" ").map { it.parseRule() })
            else -> RuleReference(ruleText.toInt())
        }
    }
}