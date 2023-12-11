import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    val input =
        """...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#....."""

    @Test
    fun part1() {
        assertEquals("374", Day11.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("1030", Day11.solve(input, 10))
        assertEquals("8410", Day11.solve(input, 100))
    }
}