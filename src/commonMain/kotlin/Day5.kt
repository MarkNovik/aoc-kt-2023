data object Day5 : AOC(5) {
    override fun part1(input: String): String {
        val seeds = seeds(input)
        val almanac = Almanac.parse(input)
        return seeds.minOf(almanac::locateSeed).toString()
    }

    @Suppress("UNREACHABLE_CODE")
    override fun part2(input: String): String {
        return "CURRENT SOLUTION IS TOO SLOW"
        val seeds = seeds(input)
        val almanac = Almanac.parse(input)
        return seeds.chunked(2) { (start, length) ->
            (start..<(start + length)).minOf(almanac::locateSeed)
        }.min().toString()
    }

    private val order = listOf(
        "seed-to-soil",
        "soil-to-fertilizer",
        "fertilizer-to-water",
        "water-to-light",
        "light-to-temperature",
        "temperature-to-humidity",
        "humidity-to-location"
    )

    private fun seeds(input: String): List<Long> = input
        .substringAfter("seeds: ")
        .substringBefore('\r')
        .substringBefore('\n')
        .split(" ").filter(String::isNotBlank).map(String::toLong)

    class Range(private val source: Long, private val destination: Long, private val length: Long) {
        fun map(item: Long): Long? =
            if (item >= source && item < (source + length)) {
                destination - (source - item)
            } else {
                null
            }
    }

    class Almanac(
        private val sections: List<AlmanacSection>
    ) {
        fun locateSeed(seed: Long) =
            sections.fold(seed) { acc, section -> section.map(acc) }

        companion object {
            fun parse(input: String): Almanac = Almanac(order.map { section ->
                AlmanacSection(input.substringAfter("$section map:")
                    .substringBefore("\r\n\r\n")
                    .substringBefore("\r\r")
                    .substringBefore("\n\n")
                    .lines().drop(1).map {
                        val (dest, src, len) = it.split(" ").filter(String::isNotBlank).map(String::toLong)
                        Range(src, dest, len)
                    })
            })
        }
    }

    class AlmanacSection(
        private val ranges: List<Range>
    ) {
        fun map(item: Long): Long =
            ranges.firstNotNullOfOrNull { it.map(item) } ?: item
    }
}