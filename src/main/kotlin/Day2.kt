import Day2.Cubes.*

data object Day2 : AOC(2) {
    override fun part1(input: String): String =
        input.lineSequence().map(Game::parse).filter { game ->
            game.bundles.all {
                when(it) {
                    is Red -> it.amount <= 12
                    is Green -> it.amount <= 13
                    is Blue -> it.amount <= 14
                }
            }
        }.sumOf(Game::id).toString()

    override fun part2(input: String): String =
        input.lineSequence().map(Game::parse).sumOf { game ->
            val red = game.bundles.filterIsInstance<Red>().maxOf(Cubes::amount)
            val green = game.bundles.filterIsInstance<Green>().maxOf(Cubes::amount)
            val blue = game.bundles.filterIsInstance<Blue>().maxOf(Cubes::amount)
            red * green * blue
        }.toString()

    private sealed class Cubes(val amount: Int) {
        class Red(amount: Int): Cubes(amount)
        class Green(amount: Int): Cubes(amount)
        class Blue(amount: Int): Cubes(amount)
        
        companion object {
            fun parse(line: String): Cubes {
                val (num, color) = line.trim().split(" ")
                return when (color.lowercase()) {
                    "red" -> Red(num.toInt()) 
                    "green" -> Green(num.toInt()) 
                    "blue" -> Blue(num.toInt())
                    else -> error("Unknown color `$color`")
                }
            }
        }
    }
    
    private class Game(val id: Int, val bundles: List<Cubes>) {
        companion object {
            fun parse(line: String): Game {
                val (strId, strBundles) = line.split(":")
                val id = strId.filter(Char::isDigit).toInt()
                val bundles = strBundles.split(";", ",").map(Cubes::parse)
                return Game(id, bundles)
            }
        }
    }
}