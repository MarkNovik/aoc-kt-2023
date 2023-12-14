import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    val input = """???.### 1,1,3
.??..??...?##. 1,1,3
?#?#?#?#?#?#?#? 1,3,1,6
????.#...#... 4,1,1
????.######..#####. 1,6,5
?###???????? 3,2,1"""

    @Test
    fun part1() {
        assertEquals("21", Day12.part1(input))
    }

    @Test
    fun groupApply1() {
        val cr = Day12.ConditionRecord("???.###")
        assertEquals(listOf(0, 1, 2), cr.apply(1))
        assertEquals(listOf(0, 4), cr.apply(3))
        assertEquals(listOf(listOf(0, 2, 4)), cr.applyAll(listOf(1, 1, 3)))
    }

    @Test
    fun groupApply2() {
        //#??#????.?##??#????. 1,4,1,3,1,3
        val cr = Day12.ConditionRecord("#??#????.?##??#????.")
        println()
        assertEquals(listOf(listOf(0, 2, 7, 9, 14, 16), listOf(0, 2, 7, 10, 14, 16)), cr.applyAll(listOf(1,4,1,3,1,3)))
    }
}