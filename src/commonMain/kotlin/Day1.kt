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

    private inline fun findDigit(
        str: String,
        getDigit: (Sequence<IndexedValue<Int>>) -> IndexedValue<Int>?,
        getNamed: (List<IndexedValue<Int>>) -> IndexedValue<Int>?,
        cmp: (Int) -> Boolean,
    ): Int {
        val digit = getDigit(str.asSequence().withIndex()
            .mapNotNull { (index, c) -> c.digitToIntOrNull()?.let { IndexedValue(index, it) } })
        val named = getNamed(numbers.mapIndexedNotNull { num, name ->
            str.indexOfAny(SingleElement(name)).takeIf { it != -1 }?.let { IndexedValue(it, num + 1) }
        })
        return when {
            digit != null && named != null -> if (cmp(digit.index compareTo named.index)) digit.value else named.value
            digit != null -> digit.value
            named != null -> named.value
            else -> error("Unreachable: Puzzle input must contain proper data")
        }
    }

    private fun firstDigit(str: String): Int = findDigit(
        str,
        { it.firstOrNull() },
        { it.minByOrNull { it.index } },
        { it < 0 }
    )

    private fun lastDigit(str: String): Int = findDigit(
        str,
        { it.lastOrNull() },
        { it.maxByOrNull { it.index } },
        { it > 0 }
    )

    override fun part1(input: String): Any = input
        .lineSequence()
        .sumOf { it.first(Char::isDigit).digitToInt() * 10 + it.last(Char::isDigit).digitToInt() }

    override fun part2(input: String): Any = input.lineSequence()
        .sumOf { line -> firstDigit(line) * 10 + lastDigit(line) }

    private class SingleElement<T>(private val el: T) : Collection<T> {
        override val size: Int = 1
        override fun isEmpty(): Boolean = false
        override fun iterator(): Iterator<T> = iterator { yield(el) }
        override fun containsAll(elements: Collection<T>): Boolean = elements.all { it == el }
        override fun contains(element: T): Boolean = element == el
    }
}