object Series {
    fun slices(seriesLength: Int, input: String) : List<*> {
        return input.windowed(seriesLength).map { it.toList().map { Integer.parseInt("$it") } }
    }
}