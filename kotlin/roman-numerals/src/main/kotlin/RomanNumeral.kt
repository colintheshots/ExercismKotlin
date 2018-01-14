object RomanNumeral {
    private val charMap = mapOf(1000 to "M", 900 to "CM",
            500 to "D", 400 to "CD", 100 to "C", 90 to "XC",
            50 to "L", 40 to "XL", 10 to "X", 9 to "IX",
            5 to "V", 4 to "IV", 1 to "I")
    fun value(input: Int) : String {
        var output = ""
        var remaining = input
        charMap.forEach { pair ->
            (1..(remaining / pair.key)).forEach { output += pair.value }
            remaining %= pair.key
        }
        return output
    }
}