class ChangeCalculator (list: List<Int>) {

    private val sortedList = list.sortedDescending()

    fun computeMostEfficientChange(total : Int) : List<Int> {
        require(total >= 0) {"Negative totals are not allowed."}
        if (total == 0) return emptyList() // no coins make zero change

        val trimmed = sortedList.dropWhile { it > total } // drop useless values
        var pairs = pairsOf(trimmed, total)
        while(pairs.isNotEmpty() && pairs.none { it.first == total }) { // stop if no pairs remain or found match
            pairs = pairsOf(trimmed, total, pairs)
        }
        if (pairs.isEmpty())
            throw IllegalArgumentException("The total $total cannot be represented in the given currency.")
        else return pairs.filter { it.first == total }
                .minBy { it.second.size }?.second
                ?.reversed() ?: throw IllegalArgumentException("No minimum found")
    }

    // Memoize sums of lists to reduce problem complexity, i.e. dynamic programming
    private fun pairsOf(left: Collection<Int>,
                        total: Int,
                        pairs: List<Pair<Int, List<Int>>> = listOf()): List<Pair<Int, List<Int>>> {
        return if (pairs.isEmpty()) { // build up initial list with all pairs including single coins
            left.flatMap { first ->
                left.filter { it <= first }
                        .plus(0) // handle single coins by pairing values with zero
                        .map { second -> Pair(first + second, listOf(first, second)) }
                        .filter { it.first <= total }
                        .map { Pair(it.first, it.second.filter { it>0 }) } // filter out zeroes
            }
        } else { // add remaining candidate coins to each remaining pair
            pairs.flatMap { pair ->
                left.filter { it <= pair.second.last() }
                        .map { second -> Pair(pair.first + second, pair.second + second) }
                        .filter { it.first <= total }
            }
        }
    }
}