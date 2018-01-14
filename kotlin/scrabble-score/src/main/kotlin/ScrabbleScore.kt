class ScrabbleScore {
    companion object {
        fun scoreWord(input: String) : Int {
            return input.toUpperCase()
                .map {
                    when {
                        "AEIOULNRST".contains(it) -> 1
                        "DG".contains(it) -> 2
                        "BCMP".contains(it) -> 3
                        "FHVWY".contains(it) -> 4
                        'K' == it -> 5
                        "JX".contains(it) -> 8
                        "QZ".contains(it) -> 10
                        else -> 0
                    }
                }.sumBy { it }
        }
    }
}