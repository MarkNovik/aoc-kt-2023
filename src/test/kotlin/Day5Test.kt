import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun part1() {
        assertEquals("35", Day5.part1(input))
    }

    @Ignore
    @Test
    fun part2() {
        assertEquals("46", Day5.part2(input))
    }

    val input = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
}