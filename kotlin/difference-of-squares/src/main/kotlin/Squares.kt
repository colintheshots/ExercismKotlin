class Squares(val value: Int) {
    fun squareOfSum() : Int {
        val sum = (0..value).sum()
        return sum*sum
    }

    fun sumOfSquares() : Int {
        return (0..value).map { it * it }.sum()
    }

    fun difference() : Int {
        return squareOfSum() - sumOfSquares()
    }
}