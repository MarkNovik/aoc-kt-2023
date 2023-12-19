import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    private val input = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

    @Test
    fun part1() {
        assertEquals("6440", Day7.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("5905", Day7.part2(input))
    }
}