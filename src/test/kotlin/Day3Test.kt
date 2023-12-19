import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    private val input = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."""

    @Test
    fun part1() {
        assertEquals("4361", Day3.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("467835", Day3.part2(input))
    }
}