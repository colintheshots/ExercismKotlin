class Hamming {
    companion object {
        fun compute(first: String, second: String) : Int {
            require(first.length == second.length, {"left and right strands must be of equal length."})
            return first.indices.count { first[it] != second[it] }
        }
    }
}