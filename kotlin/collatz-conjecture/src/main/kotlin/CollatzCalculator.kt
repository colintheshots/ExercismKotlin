class CollatzCalculator {
    companion object {
        fun computeStepCount(seed: Int) : Int {
            require(seed > 0) {"Only natural numbers are allowed"}

            var i = seed
            var count = 0
            while (i != 1) {
                i = when (i % 2) {
                    0 -> i / 2
                    1 -> 3 * i + 1
                    else -> throw IllegalArgumentException()
                }
                count++
            }
            return count
        }
    }
}