object ETL {
    fun transform(oldMap: Map<Int, List<Char>>) : Map<Char, Int> {
        val result = mutableMapOf<Char, Int>()
        oldMap.entries.forEach {value ->
            value.value.forEach {
                result.put(it.toLowerCase(), value.key)
            }
        }
        return result
    }
}