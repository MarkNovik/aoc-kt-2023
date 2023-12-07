import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlin.time.measureTimedValue

fun main() {
    Day1.run()
    Day2.run()
    Day3.run()
    Day4.run()
    Day5.run()
    Day6.run()
    Day7.run()
}

sealed class AOC(private val day: Int) {
    abstract fun part1(input: String): String
    abstract fun part2(input: String): String

    fun run() {
        print("Day $day: ")
        runCatching { SystemFileSystem.source(Path("input/day$day.txt")).buffered().readString() }.fold(
            onSuccess = { input ->
                print("\n\tPart1: ")
                val (res1, time1) = measureTimedValue { part1(input) }
                println("$res1, $time1")
                print("\tPart2: ")
                val (res2, time2) = measureTimedValue { part2(input) }
                println("$res2, $time2")
            },
            onFailure = { err ->
                println("Error while reading input: $err")
            }
        )
    }
}