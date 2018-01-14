class Dna (sequence: String) {
    init {
        require(sequence.all { c -> c in "ACGT" })
    }

    val nucleotideCounts: Map<Char, Int> = sequence.groupBy { it }
            .mapValuesTo(mutableMapOf('A' to 0,'C' to 0, 'G' to 0, 'T' to 0)) { it.value.size }
}