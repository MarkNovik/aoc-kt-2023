import kotlin.math.abs

data object Day11 : AOC(11) {
    override fun part1(input: String): String = solve(input, 2)

    override fun part2(input: String): String = solve(input, 1_000_000)

    fun solve(input: String, multiplier: Long): String {
        val map = expandColumns(expandRows(input.lines()))
        val galaxies = galaxies(map, multiplier)
        return galaxies.indices.sumOf { i ->
            (galaxies.indices.drop(i + 1)).sumOf { j ->
                dist(galaxies[i], galaxies[j])
            }
        }.toString()
    }

    fun dist(a: Pair<Long, Long>, b: Pair<Long, Long>): Long =
        abs(a.first - b.first) + abs(a.second - b.second)

    fun galaxies(map: List<String>, expansionMultiplier: Long): List<Pair<Long, Long>> =
        map.flatMapIndexed { y: Int, line: String ->
            line.mapIndexedNotNull { x, c ->
                if (c == '#') {
                    val ex = line.slice(0..<x).sumOf {
                        if (it == 'O') expansionMultiplier
                        else 1L
                    }
                    val ey = map.slice(0..<y).sumOf { l ->
                        if (l[x] == 'O') expansionMultiplier
                        else 1L
                    }
                    ex to ey
                }
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
}