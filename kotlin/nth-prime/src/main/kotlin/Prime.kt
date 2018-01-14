object Prime {
    fun nth(inputNumber: Int) : Int {
        require(inputNumber > 0) {"There is no zeroth prime."}
        return generateSequence(
                2 to generateSequence(3) {it + 2}) {
            val sequenceIterator = it.second.iterator()
            val nextPrime = sequenceIterator.next()
            nextPrime to sequenceIterator.asSequence().filter {
                it % nextPrime != 0
            }
        }
                .take(inputNumber)
                .map { it -> it.first }
                .last()
    }
}