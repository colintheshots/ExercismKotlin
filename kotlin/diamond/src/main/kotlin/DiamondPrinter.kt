class DiamondPrinter {

    fun printToList(myChar: Char) : List<String> {
        require(myChar in 'A'..'Z')
        val charRange = 'A'.rangeTo(myChar)
        val charRangeSize = charRange.count()
        val arraySize = charRangeSize * 2 - 1

        val top = charRange.mapIndexed{index, c ->
                (1..arraySize).map {
                    when (it) {
                        charRangeSize - index -> c
                        charRangeSize + index -> c
                        else -> ' '
                    }
                }.joinToString("")
            }
        val bottom = top.reversed().drop(1)
        return (top.plus(bottom))
    }
}
