data object Day13 : AOC(13) {
    override fun part1(input: String): String = parseInput(input)
        .map(::findSymmetry)
        .partition { it is Symmetry.Vertical }.let { (vert, horz) ->
            vert.sumOf(Symmetry::loc) + 100 * horz.sumOf(Symmetry::loc)
        }.toString()


    override fun part2(input: String): String = "TODO"

    fun findSymmetry(mat: Note): Symmetry = listOfNotNull(
        symmetrySize(mat.rows)?.let(Symmetry::Horizontal),
        symmetrySize(mat.columns)?.let(Symmetry::Vertical)
    ).maxByOrNull(Symmetry::loc) ?: error("$mat hadn't Symmetry")


    fun symmetrySize(list: List<String>): Int? {
        for ((start, column) in list.withIndex()) {
            val coincidences = list.indicesOfAll { it == column }
            coincidences.findLast { end ->
                start != end &&
                        (start..end).all { list[it] == list[end - (it - start)] }
            }?.let { end ->
                return start + ((start..end).size / 2)
            }
        }
        return null
    }

    fun parseInput(input: String): List<Note> = input.splitBlank().map(::parseNote)
    fun parseNote(noteInput: String) = Note(noteInput.lines())

    sealed interface Symmetry {
        val loc: Int

        data class Vertical(override val loc: Int) : Symmetry
        data class Horizontal(override val loc: Int) : Symmetry
    }

    class Note(val rows: List<String>) {

        val width = rows.firstOrNull()?.length ?: 0
        val height = rows.size

        val columns = buildList(width) {
            repeat(width) { x ->
                add(buildString(height) {
                    repeat(height) { y ->
                        append(rows[y][x])
                    }
                })
            }
        }

        override fun toString(): String = rows.joinToString("\n")

        init {
            require(rows.distinctBy { it.length }.size == 1) { "Matrix must be a rectangle" }
        }
    }
}