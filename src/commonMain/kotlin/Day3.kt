data object Day3 : AOC(3) {
    override fun part1(input: String): String = input
        .lines().let(::Engine)
        .partNumbers().distinct()
        .sumOf { it.num.el }.toString()

    override fun part2(input: String): String = input
        .lines().let(::Engine)
        .gears().values
        .filter { it.size == 2 }
        .sumOf { (a, b) -> a * b }.toString()

    class Engine(private val scheme: List<String>) {
        fun partNumbers() = buildList {
            scheme.forEachIndexed { y, line ->
                line.forEachIndexed { x: Int, c: Char ->
                    if (c != '.' && !c.isDigit()) {
                        for (dx in (-1)..1)
                            for (dy in (-1)..1) {
                                if (dx != 0 || dy != 0) findNumber(Pointed(x, y, c), y + dy, x + dx)?.let(this::add)
                            }
                    }
                }
            }
        }

        fun gears() = buildMap<_, MutableSet<Int>> {
            scheme.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    if (c == '*') {
                        for (dx in (-1)..1) {
                            for (dy in (-1)..1) {
                                if (dx != 0 || dy != 0) {
                                    findNumber(Pointed(x, y, c), y + dy, x + dx)?.let { (part, num) ->
                                        getOrPut(part, ::mutableSetOf) += num.el
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
            .mapValues { it.value.toList() }
            .filterValues { it.size == 2 }


        private tailrec fun findNumber(
            part: Pointed<Char>,
            y: Int,
            fromX: Int,
            toX: Int = fromX
        ): PartNumber? {
            if (scheme[y].slice(fromX..toX).any { !it.isDigit() }) return null
            val left = scheme[y].getOrNull(fromX - 1)?.isDigit()
            val right = scheme[y].getOrNull(toX + 1)?.isDigit()
            return when {
                left != true && right != true -> scheme[y].slice(fromX..toX).toIntOrNull()
                    ?.let {
                        PartNumber(part, Pointed(fromX, y, it))
                    }

                left == true -> findNumber(part, y, fromX - 1, toX)
                right == true -> findNumber(part, y, fromX, toX + 1)
                else -> findNumber(part, y, fromX - 1, toX + 1)
            }
        }
    }

    data class Pointed<T>(val x: Int, val y: Int, val el: T)
    data class PartNumber(val part: Pointed<Char>, val num: Pointed<Int>)
}