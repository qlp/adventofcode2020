package nl.qlp.aoc20.day10

fun List<Int>.solution(joltage: Int = 0): MutableList<Int>? {
    val candidates = filter { it > joltage }

    return if (candidates.size == 1) {
        if (joltage.validLast(candidates.single())) {
            mutableListOf(joltage, candidates.single())
        } else {
            null
        }
    } else {
        val next = candidates.minOrNull()!!

        if (joltage.validNext(next)) {
            solution(next)?.apply { add(0, joltage) }
        } else {
            null
        }
    }
}

fun Int.validNext(joltage: Int) = IntRange(1, 3).contains(joltage - this)

fun Int.validLast(joltage: Int) = joltage - this == 3
