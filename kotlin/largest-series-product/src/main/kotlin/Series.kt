import java.lang.Character.getNumericValue

class Series(private val input: String) {
    init {
        require(!input.contains(Regex("\\D")))
    }
    fun getLargestProduct(size: Int) : Int {
        require(size <= input.length)
        require(size >= 0)
        val digits = input.map { getNumericValue(it) }
        return digits.mapIndexedNotNull {
            index, _ -> if (index + size - 1 >= digits.size) null else
                    digits.slice(index until index + size)
                            .fold(1) {acc, s -> acc * s }
        }.max() ?: 1
    }
}