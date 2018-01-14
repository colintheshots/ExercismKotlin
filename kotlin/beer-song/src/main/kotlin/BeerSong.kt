object BeerSong {
    fun verses(begin: Int, end: Int) : String {
        return (begin downTo end).joinToString("\n") {
            "${it.beer().toCamelCase()} of beer on the wall, ${it.beer()} of beer.\n" +
                "${it.descriptor()}, ${it.after()} of beer on the wall.\n"
        }
    }
}

fun String.toCamelCase() : String = first().toUpperCase() + substring(1)

fun Int.descriptor() : String {
    return when (this) {
        1 -> "Take it down and pass it around"
        0 -> "Go to the store and buy some more"
        else -> "Take one down and pass it around"
    }
}

fun Int.beer() : String {
    return when (this) {
        1 -> "1 bottle"
        0 -> "no more bottles"
        else -> "$this bottles"
    }
}

fun Int.after() : String {
    return when (this) {
        0 -> 99.beer()
        else -> (this - 1).beer()
    }
}