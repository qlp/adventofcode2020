package nl.qlp.aoc20.day7

data class Rule(val line: String) {
    fun bag() = Bag(line.substring(0, line.indexOf(" bags")))

    fun constraints() = line.substring(line.indexOf("contain ") + "contain ".length, line.length - 1)
            .split(", ")
            .mapNotNull {
                if (it == "no other bags") null else Constraint(
                        count = it.substring(0, it.indexOf(" ")).toInt(),
                        bag = Bag(it.substring(it.indexOf(" ") + 1, it.indexOf(" bag"))))
            }

    fun holds(bag: Bag) = constraints().filter { it.bag == bag }.count() > 0
}