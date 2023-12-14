data object Day12 : AOC(12) {
    override fun part1(input: String): String = input.lines().sumOf { line ->
        val (cr, patterns) = parseLine(line)
        cr.applyAll(patterns).size
    }.toString()

    override fun part2(input: String): String = "TODO"

    fun parseLine(line: String): Pair<ConditionRecord, List<Int>> {
        val (string, patterns) = line.split(" ")
        return ConditionRecord(string) to patterns.split(",").map(String::toInt)
    }

    class ConditionRecord(val chars: String) {
        fun apply(pattern: Int, drop: Int = 0): List<Int> = chars.indices.drop(drop).dropLast(pattern - 1).filter { offset ->
            val before = chars.getOrNull(offset - 1) != '#'
            val itself = chars.drop(offset).take(pattern).none('.'::equals)
            val after = chars.getOrNull(offset + pattern) != '#'
            before && itself && after
        }

        fun applyAll(patterns: List<Int>): List<List<Int>> {
            fun applyAllImpl(patterns: List<Int>, drop: Int): List<ArrayDeque<Int>> = when {
                patterns.isEmpty() -> emptyList()
                patterns.size == 1 -> apply(patterns.single(), drop).map { ArrayDeque(listOf(it)) }
                else -> apply(patterns.first(), drop).flatMap { offset ->
                    applyAllImpl(
                        patterns.drop(1),
                        patterns.first() + offset + 1
                    ).onEach { it.addFirst(offset) }
                }
            }
            return applyAllImpl(patterns, 0).distinct()
        }
    }
}