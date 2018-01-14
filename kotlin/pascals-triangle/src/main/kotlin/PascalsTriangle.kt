object PascalsTriangle {
    fun computeTriangle(int: Int) : List<List<Int>> {
        require(int >= 0) {"Rows can't be negative!"}
        return (0 until int).fold(mutableListOf()) { triangle, row ->
            triangle.add(
                    (0 until row).fold(listOf(1)) { acc, i ->
                        acc.plus(acc[i] * (row - i) / (i + 1))
                    })
            triangle
        }
    }
}