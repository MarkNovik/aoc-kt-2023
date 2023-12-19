import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    val input1 = """-L|F7
7S-7|
L|7||
-L-J|
L|-JF"""

    val input2 = """7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ"""

    @Test
    fun part1() {
        assertEquals("4", Day10.part1(input1))
        assertEquals("8", Day10.part1(input2))
    }
}