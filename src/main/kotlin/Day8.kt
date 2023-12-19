import kotlin.math.absoluteValue

data object Day8 : AOC(8) {

    override fun part1(input: String): String {
        val (instructions, nodes) = parseInput(input)
        val iter = instructions.iterator()
        return generateSequence("AAA") {
            when (iter.next()) {
                Turn.LEFT -> nodes[it]!!.left
                Turn.RIGHT -> nodes[it]!!.right
            }
        }.indexOfFirst { it == "ZZZ" }.toString()
    }

    override fun part2(input: String): String {
        val (instructions, nodes) = parseInput(input)
        return nodes.keys.filter { it.endsWith('A') }.map { name ->
            sequence {
                var state = name
                var index = 0
                instructions.forEach { turn ->
                    index++
                    state = turn.action(nodes[state]!!)
                    if (state.endsWith('Z')) yield(index)
                }
            }.zipWithNext(Int::minus).first().toLong().absoluteValue
        }.reduce(::lcm).toString()
    }

    private val nodeRegex = Regex("""(\w{3})\s=\s\((\w{3}),\s(\w{3})\)""")

    private tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    private fun lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

    private fun parseInput(input: String): Pair<Instructions, Map<String, Node>> {
        val lines = input.lines().iterator()
        return Pair(lines.next().let(::Instructions), buildMap {
            lines.next()
            for (line in lines) {
                val (name, left, right) = nodeRegex.matchEntire(line)!!.destructured
                put(name, Node(left, right))
            }
        })
    }

    class Node(
        val left: String, val right: String
    )

    enum class Turn {
        LEFT {
            override fun action(n: Node): String = n.left
        },
        RIGHT {
            override fun action(n: Node): String = n.right
        };

        abstract fun action(n: Node): String
    }

    class Instructions(init: String) : Iterable<Turn> {
        private val turns = init.uppercase().map {
            when (it) {
                'L' -> Turn.LEFT
                'R' -> Turn.RIGHT
                else -> error("Unknown turn `$it`")
            }
        }

        override fun iterator(): Iterator<Turn> = object : Iterator<Turn> {
            private var index = 0

            override fun hasNext(): Boolean = true

            override fun next(): Turn {
                val c = turns[index]
                index = (index + 1) % turns.size
                return c
            }
        }
    }
}