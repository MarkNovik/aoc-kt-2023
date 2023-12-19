import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    val vertInput = "#.##..##.\n..#.##.#.\n##......#\n##......#\n..#.##.#.\n..##..##.\n#.#.##.#."
    val horzInput = "#...##..#\n#....#..#\n..##..###\n#####.##.\n#####.##.\n..##..###\n#....#..#"

    val input =
        "#.##..##.\n..#.##.#.\n##......#\n##......#\n..#.##.#.\n..##..##.\n#.#.##.#.\n\n#...##..#\n#....#..#\n..##..###\n#####.##.\n#####.##.\n..##..###\n#....#..#"

    @Test
    fun symmetryTest() {
        assertEquals(Day13.Symmetry.Vertical(5), Day13.findSymmetry(Day13.parseNote(vertInput)))
        assertEquals(Day13.Symmetry.Horizontal(4), Day13.findSymmetry(Day13.parseNote(horzInput)))
    }

    @Test
    fun part1() {
        assertEquals("405", Day13.part1(input))
    }
}