class BaseConverter(val base: Int, val digits: IntArray) {
    init {
        require(base >= 2) {"Bases must be at least 2."}
        require(digits.isNotEmpty()) {"You must supply at least one digit."}
        require(digits.all { it < base }) {"All digits must be strictly less than the base."}
        require(digits.size < 2 || digits.first() != 0) {"Digits may not contain leading zeros."}
        require(digits.all { it >= 0 }) {"Digits may not be negative."}
    }

    fun convertToBase(newBase: Int) : IntArray {
        require(newBase >= 2) {"Bases must be at least 2."}
        var sum = 0.0
        digits.mapIndexed { index, i ->
            sum += Math.pow(base.toDouble(), (digits.size - 1 - index).toDouble()) * i
        }
        val result = mutableListOf<Int>()
        (32 downTo 0).map { currentPower ->
            val currentDigit = sum / Math.pow(newBase.toDouble(), currentPower.toDouble())
            if (currentDigit >= 1.0) {
                result.add(currentDigit.toInt())
                sum %= Math.pow(newBase.toDouble(), currentPower.toDouble())
            } else if (result.any { it > 0.0 }) result.add(currentDigit.toInt())
        }
        if (result.isEmpty()) return intArrayOf(0)
        return result.toIntArray()
    }
}