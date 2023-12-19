import java.io.File
import kotlin.reflect.KClass
import kotlin.time.measureTimedValue

fun main() {
    Day12.run()
    //AOC::class.sealedSubclasses.mapNotNull(KClass<out AOC>::objectInstance).forEach(AOC::run)
}

sealed class AOC(private val day: Int) {
    abstract fun part1(input: String): String
    abstract fun part2(input: String): String

    fun run() {
        print("Day $day: ")
        runCatching { File("input/day$day.txt").readText() }.fold(
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