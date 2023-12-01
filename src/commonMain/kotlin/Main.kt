import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlin.time.measureTimedValue

fun main() {
    run(Day1)
}

fun run(day: AOC) {
    print("Day ${day.day}: ")
    runCatching { SystemFileSystem.source(Path("input/day${day.day}.txt")).buffered().readString() }.fold(
        onSuccess = { input ->
            print("\n\tPart1: ")
            val (res1, time1) = measureTimedValue { day.part1(input) }
            println("$res1, $time1")
            print("\tPart2: ")
            val (res2, time2) = measureTimedValue { day.part2(input) }
            println("$res2, $time2")
        },
        onFailure = { err ->
            println("Error while reading input: $err")
        }
    )
}

sealed class AOC(val day: Int) {
    abstract fun part1(input: String): Any
    abstract fun part2(input: String): Any
}