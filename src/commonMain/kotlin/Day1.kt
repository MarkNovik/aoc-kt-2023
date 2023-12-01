data object Day1 : AOC(1) {
    private val numbers = arrayOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
    )

    private fun firstDigit(input: String): Int {
        val chars = StringBuilder(input)
        while (chars.isNotEmpty()) {
            chars.first().digitToIntOrNull()?.let { return it }
            numbers.forEachIndexed { num, name ->
                if (chars.startsWith(name)) return num + 1
            }
            chars.deleteAt(0)
        }
        error("Unreachable: Puzzle input must be correct")
    }

    private fun lastDigit(input: String): Int {
        val chars = StringBuilder(input)
        while (chars.isNotEmpty()) {
            chars.last().digitToIntOrNull()?.let { return it }
            numbers.forEachIndexed { num, name ->
                if (chars.endsWith(name)) return num + 1
            }
            chars.deleteAt(chars.lastIndex)
        }
        error("Unreachable: Puzzle input must be correct")
    }

    override fun part1(input: String): String = input.lineSequence()
        .sumOf { it.first(Char::isDigit).digitToInt() * 10 + it.last(Char::isDigit).digitToInt() }
        .toString()

    override fun part2(input: String): String = input.lineSequence()
        .sumOf { firstDigit(it) * 10 + lastDigit(it) }
        .toString()
}