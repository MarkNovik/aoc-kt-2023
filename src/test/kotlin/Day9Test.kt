import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    val input = "0 3 6 9 12 15\n1 3 6 10 15 21\n10 13 16 21 30 45"

    @Test
    fun part1() {
        assertEquals("114", Day9.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("2", Day9.part2(input))
    }
}