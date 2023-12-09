data object Day9 : AOC(9) {
    override fun part1(input: String): String = solve(input, ::extrapolateRight)

    override fun part2(input: String): String = solve(input, ::extrapolateLeft)

    private fun solve(input: String, extrapolation: (List<Int>) -> Int): String = input
        .lines()
        .map { it.split(" ").map(String::toInt) }
        .sumOf { extrapolation(it) }
        .toString()

    private fun differentiate(nums: List<Int>) =
        nums.zipWithNext { a, b -> b - a }

    private fun extrapolateRight(nums: List<Int>): Int =
        if (nums.all { it == nums.first() }) nums.first()
        else nums.last() + extrapolateRight(differentiate(nums))

    private fun extrapolateLeft(nums: List<Int>): Int =
        if (nums.all { it == nums.first() }) nums.first()
        else nums.first() - extrapolateLeft(differentiate(nums))
}