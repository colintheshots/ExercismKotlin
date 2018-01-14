object WordCount {
    fun phrase(input: String): Map<String, Int>
    {
        return input.toLowerCase()
                .split("\'?[^a-z0-9\']+\'?".toRegex())
                .groupBy { it }
                .mapValues { it.value.size }
                .filter { it.key.isNotEmpty() }
    }
}