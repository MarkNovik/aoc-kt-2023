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

    private fun digit(input: String, position: Position): Int {
        val (charSelector, wordPredicate, removeIndex) = position
        val chars = StringBuilder(input)
        while (chars.isNotEmpty()) {
            charSelector(chars).digitToIntOrNull()?.let { return it }
            numbers.forEachIndexed { num, name ->
                if (wordPredicate(chars, name)) return num + 1
            }
            chars.deleteAt(removeIndex(chars))
        }
        error("Unreachable: Puzzle input must be correct")
    }

    private fun firstDigit(input: String): Int = digit(input, Position.First)

    private fun lastDigit(input: String): Int = digit(input, Position.Last)

    override fun part1(input: String): String = input.lineSequence()
        .sumOf { it.first(Char::isDigit).digitToInt() * 10 + it.last(Char::isDigit).digitToInt() }
        .toString()

    override fun part2(input: String): String = input.lineSequence()
        .sumOf { firstDigit(it) * 10 + lastDigit(it) }
        .toString()

    private enum class Position(
        private inline val charSelector: (CharSequence) -> Char,
        private inline val wordPredicate: (CharSequence, String) -> Boolean,
        private inline val removeIndex: (CharSequence) -> Int
    ) {
        First(CharSequence::first, CharSequence::startsWith, { 0 }),
        Last(CharSequence::last, CharSequence::endsWith, CharSequence::lastIndex);

        inline operator fun component1() = charSelector
        inline operator fun component2() = wordPredicate
        inline operator fun component3() = removeIndex
    }
}