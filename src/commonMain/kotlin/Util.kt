val IntRange.size get() = endInclusive - start + 1

fun String.splitBlank(): List<String> =
    split("\r\n\r\n", "\n\n", "\r\r")

inline fun <T> List<T>.indicesOfAll(predicate: (T) -> Boolean): List<Int> =
    mapIndexedNotNull { index, t -> index.takeIf { predicate(t) } }