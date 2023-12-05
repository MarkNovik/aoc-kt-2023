data object Day3 : AOC(3) {
    override fun part1(input: String): String = input
        .lines().let(::Engine)
        .partNumbers()
        .sumOf { it.el }.toString()

    override fun part2(input: String): String = input
        .lines().let(::Engine)
        .gears().values
        .filter { it.size == 2 }
        .sumOf { (a, b) -> a.el * b.el }.toString()

    private inline fun List<String>.forEachCharIndexed(block: (x: Int, y: Int, c: Char) -> Unit) =
        forEachIndexed { y, line -> line.forEachIndexed { x, c -> block(x, y, c) } }

    private inline fun forEachNeighbour(x: Int, y: Int, block: (dx: Int, dy: Int) -> Unit) =
        ((-1)..1).forEach { dx -> ((-1)..1).forEach { dy -> if (dx != 0 || dy != 0) block(x + dx, y + dy) } }

    private class Engine(private val scheme: List<String>) {
        fun partNumbers() = buildSet {
            scheme.forEachCharIndexed { x, y, c ->
                if (c != '.' && !c.isDigit()) addAll(adjacentNumbers(x, y))
            }
        }

        fun gears() = buildMap {
            scheme.forEachCharIndexed { x, y, c ->
                if (c == '*') put(Pointed(x, y, c), adjacentNumbers(x, y))
            }
        }

        private fun adjacentNumbers(
            x: Int, y: Int
        ): List<Pointed<Int>> = buildList(9) {
            forEachNeighbour(x, y) { dx, dy -> findNumber(dx, dy)?.let(::add) }
        }.distinct()

        private fun findNumber(
            x: Int, y: Int
        ): Pointed<Int>? {
            if (y !in scheme.indices) return null
            if (x !in scheme[y].indices) return null
            if (!scheme[y][x].isDigit()) return null
            tailrec fun findNumberImpl(
                at: Int, from: Int, to: Int
            ): Pointed<Int> {
                val left = scheme[at].getOrNull(from - 1)?.isDigit()
                val right = scheme[at].getOrNull(to + 1)?.isDigit()
                return when {
                    left != true && right != true -> Pointed(from, at, scheme[at].slice(from..to).toInt())
                    left == true -> findNumberImpl(at, from - 1, to)
                    right == true -> findNumberImpl(at, from, to + 1)
                    else -> findNumberImpl(at, from - 1, to + 1)
                }
            }
            return findNumberImpl(y, x, x)
        }
    }

    data class Pointed<T>(val x: Int, val y: Int, val el: T)
}