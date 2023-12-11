import kotlin.math.roundToInt

data object Day11 : AOC(11) {
    override fun part1(input: String): String = solve(input, 2)

    override fun part2(input: String): String = solve(input, 1_000_000)

    fun solve(input: String, multiplier: Long): String {
        val map = expandColumns(expandRows(input.lines()))
        val galaxies = galaxies(map)
        return galaxies.indices.sumOf { i ->
            (galaxies.indices.drop(i + 1)).sumOf { j ->
                dist(galaxies[i], galaxies[j], map, multiplier)
            }
        }.toString()
    }

    fun dist(a: Pair<Int, Int>, b: Pair<Int, Int>, map: List<String>, expansionMultiplier: Long): Long {
        val slope = (a.second.toDouble() - b.second) / (a.first - b.first)
        val path =
            if (slope.isInfinite()) (minOf(a.second, b.second)..maxOf(a.second, b.second)).map { a.first to it }
            else (a.first..b.first).map { x -> x to (slope * (x - a.first) + a.second).roundToInt() }
        return path.sumOf { (x, y) ->
            if (map[y][x] == 'O') expansionMultiplier
            else 1L
        }
    }

    fun galaxies(map: List<String>): List<Pair<Int, Int>> =
        map.flatMapIndexed { y: Int, line: String ->
            line.mapIndexedNotNull { x, c ->
                if (c == '#') x to y
                else null
            }
        }

    fun expandRows(map: List<String>): List<String> =
        map.map {
            if (it.all(::isEmptySpace)) it.replace('.', 'O')
            else it
        }

    fun expandColumns(map: List<String>): List<String> =
        map.map { line ->
            line.withIndex().joinToString("") { (i, c) ->
                if (map.map { it[i] }.all(::isEmptySpace)) "O"
                else "$c"
            }
        }

    fun isEmptySpace(c: Char) = c == '.' || c == 'O'

    class ExpandedSpace(
        input: String,
        val expansionMultiplier: Long
    ) {
        val map: Map<LongRange, Map<LongRange, Char>> = buildMap {
            val expanded = expandColumns(expandRows(input.lines()))
            var bigSpaces = 0

        }

        operator fun get(x: Int, y: Int): Char {
            var nx = x
            var ny = y
        }
    }
}