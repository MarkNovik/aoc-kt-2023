import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    val input = """Time:      7  15   30
Distance:  9  40  200"""
    @Test
    fun part1() {
        assertEquals("288", Day6.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("71503", Day6.part2(input))
    }
}