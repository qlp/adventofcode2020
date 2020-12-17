package nl.qlp.aoc20.day17

import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

class First {
    fun run() = World(readLinesFromInput()
            .flatMapIndexed { y, s ->
                s.mapIndexedNotNull { x, c ->
                    if (c == '#') Cube(x.toLong(), y.toLong(), 0) else null
                }
            }.toSet())
            .tick()
            .tick()
            .tick()
            .tick()
            .tick()
            .tick()
            .activeCubeCount()

    data class Cube(val x: Long, val y: Long, val z: Long) {
        fun neighbours() = LongRange(x - 1, x + 1).flatMap { x ->
            LongRange(y - 1, y + 1).flatMap { y ->
                LongRange(z - 1, z + 1).map { z ->
                    Cube(x, y, z)
                }
            }
        }.filterNot { it == this }.toSet()
    }

    data class World(val cubes: Set<Cube>) {
        fun activeCubeCount() = cubes.size

        fun tick(): World {
            val result = mutableSetOf<Cube>()

            cubes.forEach { if (IntRange(2, 3).contains(it.neighbourCount())) { result.add(it) } }

            val neighbours = cubes.flatMap { it.neighbours() }.toSet()

            neighbours.forEach { if (it.neighbourCount() == 3) { result.add(it) } }

            return World(result)
        }

        private fun Cube.neighbourCount() = neighbours().filter { cubes.contains(it) }.count()
    }
}