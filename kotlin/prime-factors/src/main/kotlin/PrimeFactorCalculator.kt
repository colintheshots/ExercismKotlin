import kotlin.coroutines.experimental.buildSequence

object PrimeFactorCalculator {
    fun <T : Number> primeFactors(input: T) : List<T> {
        var current= input.toLong()
        val factors = mutableListOf<T>()
        whileLoop@ while (current > 1L) {
            for (prime in sieve(current as T)) {
                if (current % prime.toLong() == 0L) {
                    factors.add(prime)
                    current /= prime.toLong()
                    continue@whileLoop
                }
            }
        }
        return factors
    }

    private fun <T : Number> sieve(limit: T) : Sequence<T> {
        return buildSequence {
            (2L..limit as Long).forEach { candidate ->
                (2L..limit).forEach {
                    if (candidate % it == 0L) yield(candidate as T)
                }
            }

        }
    }
}
