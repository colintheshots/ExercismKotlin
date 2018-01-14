class Sieve {
    companion object {
        fun primesUpTo(limit: Int) : List<Int> {
            val range = (2..limit)
            return range.fold((range.toList())) { acc, i ->
                acc.minus((2*i)..range.last() step i) }
        }
    }
}