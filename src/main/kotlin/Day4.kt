data object Day4 : AOC(4) {
    override fun part1(input: String): String = input
        .lines()
        .sumOf { line ->
            1 shl (card(line) - 1) and Int.MAX_VALUE
        }
        .toString()

    override fun part2(input: String): String = input
        .lines()
        .map(::card)
        .let(::countFinalCards)
        .toString()

    private fun card(line: String): Int =
        line.substringAfter(':').split('|').map(::numbers).let { (a, b) -> matches(a, b) }

    private fun numbers(s: String) =
        s.trim().split(' ').filterNot(String::isBlank).map(String::toInt)

    private fun matches(winningNumbers: List<Int>, cardNumbers: List<Int>) =
        cardNumbers.count { it in winningNumbers  }

    private fun countFinalCards(cards: List<Int>): Int {
        val memory = IntArray(cards.size) { 1 }
        cards.forEachIndexed { id, matches ->
            for (offset in 1..matches) {
                memory[id + offset] += memory[id]
            }
        }
        return memory.sum()
    }
}