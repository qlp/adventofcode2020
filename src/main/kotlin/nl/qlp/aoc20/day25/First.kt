package nl.qlp.aoc20.day25

import nl.qlp.aoc20.readFromInput
import nl.qlp.aoc20.readLinesFromInput

fun main(args: Array<String>) {
    println(First().run())
}

typealias LoopSize = Int
typealias SubjectNumber = Long
typealias EncryptionKey = Long


class First {
    fun run(): Long {
        val (doorPublicKey: SubjectNumber, cardPublicKey: SubjectNumber) = readLinesFromInput().map { it.toLong() }.toList().let { Pair(it[0], it[1])}

        return doorPublicKey.loopSize().encryptionKey(cardPublicKey)
    }

    fun Long.nextLoop(subjectNumber: SubjectNumber = 7) = (this * subjectNumber) % 20201227L

    fun LoopSize.encryptionKey(subjectNumber: SubjectNumber): EncryptionKey {
        var result = 1L

        repeat(this) {
            result = result.nextLoop(subjectNumber)
        }

        return result

    }

    fun SubjectNumber.loopSize(): LoopSize {
        var candidate = 1L
        var result = 0
        do {
            result++
            candidate = candidate.nextLoop()
        } while(candidate != this)

        return result
    }
}