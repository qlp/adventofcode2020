package nl.qlp.aoc20.day9

fun List<Long>.sums(): Set<Long> {
    val base = toSet().toList()

    val result = mutableSetOf<Long>()

    base.forEachIndexed { index, left ->
        base.subList(index + 1, base.size).forEach { right ->
            result.add(left + right)
        }
    }

    return result.toSet()
}