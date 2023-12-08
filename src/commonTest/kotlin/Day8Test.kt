import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun part1() {
        val input1 = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)"""

        val input2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"""

        assertEquals("2", Day8.part1(input1))
        assertEquals("6", Day8.part1(input2))
    }

    @Test
    fun part2() {
        val input = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)"""

        assertEquals("6", Day8.part2(input))
    }
}
