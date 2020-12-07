//package nl.qlp.aoc20.day6
//
//import nl.qlp.aoc20.readFromInput
//
//fun main(args: Array<String>) {
//    println("${Second().run()}")
//}
//
//class Second {
//    fun run() = readFromInput()
//        .split("\n\n")
//        .map { group -> group
//                .split("\n")
//                .map { answers -> answers.asSequence().toSet() }
//                .reduce { acc, s -> acc intersect s }
//                .size
//        }
//        .sumBy { it }
//}
