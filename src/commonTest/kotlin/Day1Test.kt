import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1() {
        val input = "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet"
        assertEquals("142", Day1.part1(input))
    }

    @Test
    fun part2() {
        val input = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen"
        assertEquals("281", Day1.part2(input))
    }
}