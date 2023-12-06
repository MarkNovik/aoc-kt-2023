data object Day6 : AOC(6) {
    override fun part1(input: String): String {
        val races = races(input)
        return races.map(Race::winMargin).fold(1, Int::times).toString()
    }

    override fun part2(input: String): String = race(input).winMargin().toString()

    private fun races(input: String): List<Race> =
        input.lines().map {
            it.substringAfter(':').split(' ')
                .filterNot(String::isBlank).map(String::toLong)
        }.let { it[0].zip(it[1], ::Race) }

    private fun race(input: String): Race = input.lines().map {
        it.substringAfter(':').filter { it.isDigit() }.toLong()
    }.let { (t, d) -> Race(t, d) }

    class Race(val time: Long, val distance: Long) {
        fun winMargin(): Int =
            marginOfError(time / 2L).count()//.run { endInclusive - start }

        private tailrec fun marginOfError(from: Long, to: Long = from): LongRange {
            val left = ((time - (from - 1)) * (from - 1)) > distance
            val right = ((time - (to + 1)) * (to + 1)) > distance
            return when {
                left && right -> marginOfError(from - 1, to + 1)
                left && !right -> marginOfError(from - 1, to)
                !left && right -> marginOfError(from, to + 1)
                else -> from..to
            }
        }
    }
}