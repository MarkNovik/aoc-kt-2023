import Day10.Pipe.*
import Day10.Side.*
import kotlin.jvm.JvmInline
import kotlin.math.abs

data object Day10 : AOC(10) {
    override fun part1(input: String): String {
        val map = parseSketch(input)
        return map.loop().size.div(2).toString()
    }

    // Stolen from @SansSkill
    override fun part2(input: String): String {
        val map = input.lines()
        val loop = parseSketch(input).loop().map { it.x to it.y }
        return (1..<map.size - 1).sumOf { x ->
            val idx = map[x].indices.filter { y ->
                val i1 = loop.indexOf(x to y)
                val i2 = loop.indexOf(x + 1 to y)
                i1 != -1 && i2 != -1 && (abs(i1 - i2) == 1 || i1 in listOf(0, loop.lastIndex) && i2 in listOf(
                    0,
                    loop.lastIndex
                ))
            }
            (idx.indices step 2).sumOf { i ->
                (idx[i]..idx[i + 1]).count { y -> x to y !in loop }
            }
        }.toString()
    }

    @JvmInline
    value class Sketch(val map: List<List<Pipe>>) {
        fun neighboursFor(p: Pipe) = buildList<Pipe> {
            if (NORTH in p.connections) map.getOrNull(p.y - 1)?.get(p.x)
                ?.let { if (SOUTH in it.connections) add(it) }
            if (SOUTH in p.connections) map.getOrNull(p.y + 1)?.get(p.x)
                ?.let { if (NORTH in it.connections) add(it) }
            if (EAST in p.connections) map[p.y].getOrNull(p.x + 1)
                ?.let { if (WEST in it.connections) add(it) }
            if (WEST in p.connections) map[p.y].getOrNull(p.x - 1)
                ?.let { if (EAST in it.connections) add(it) }
        }

        fun loop(
        ): List<Pipe> {
            var prev: Pipe = map.flatMap { it.filterIsInstance<StPos>() }.single()
            var (curr, end) = neighboursFor(prev)
            val res = mutableListOf<Pipe>(prev)
            while (curr != end) {
                res += curr
                val next = neighboursFor(curr).single { it != prev }
                prev = curr
                curr = next
            }
            return res + end
        }
    }

    fun parseSketch(input: String): Sketch = Sketch(input.lines().mapIndexed { y, line ->
        line.mapIndexed { x, c ->
            when (c) {
                '.' -> Ground(x, y)
                '-' -> HPipe(x, y)
                '|' -> VPipe(x, y)
                'F' -> FBend(x, y)
                'J' -> JBend(x, y)
                'L' -> LBend(x, y)
                '7' -> SBend(x, y)
                'S' -> StPos(x, y)
                else -> error("Unknown tile `$c`")
            }
        }
    }
)
    enum class Side {
        NORTH, EAST, SOUTH, WEST
    }

    sealed interface Pipe {
        val x: Int
        val y: Int
        val connections: List<Side>

        data class FBend(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(EAST, SOUTH)
        }

        data class LBend(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(NORTH, EAST)
        }

        data class JBend(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(NORTH, WEST)
        }

        data class SBend(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(SOUTH, WEST)
        }

        data class VPipe(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(NORTH, SOUTH)
        }

        data class HPipe(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = listOf(EAST, WEST)
        }

        data class Ground(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = emptyList()
        }

        data class StPos(override val x: Int, override val y: Int) : Pipe {
            override val connections: List<Side> = Side.entries
        }
    }
}