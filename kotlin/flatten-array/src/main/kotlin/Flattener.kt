object Flattener {
    fun flatten(intList: List<*>) : List<*> {
        return intList.flatMap {
            when (it) {
                is List<*> -> flatten(it)
                else -> listOf(it)
            }
        }.filterNotNull()
    }
}