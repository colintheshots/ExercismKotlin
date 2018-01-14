enum class Relationship {
    EQUAL, SUBLIST, SUPERLIST, UNEQUAL
}

fun <T> List<T>.relationshipTo(input: List<T>) : Relationship {
    val superset = this.containsAll(input)
    val contained = input.containsAll(this)
    return when {
        superset && contained -> Relationship.EQUAL
        superset -> Relationship.SUPERLIST
        contained -> Relationship.SUBLIST
        else -> Relationship.UNEQUAL
    }
}