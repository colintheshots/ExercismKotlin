class SumOfMultiples {
    companion object {
        fun sum(set: Set<Int>, limit: Int) : Int {
            val multiples = mutableSetOf<Int>()
            for (multiple in set) {
                (multiple until limit step multiple).forEach { multiples += it }
            }
            return multiples.sum()
        }
    }
}