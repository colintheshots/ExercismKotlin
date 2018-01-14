
enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    require(naturalNumber > 0) {"$naturalNumber is not a natural number"}
    val aliquotSum = (1..naturalNumber / 2)
            .filter { naturalNumber % it == 0 }.sum()
    return when {
        aliquotSum > naturalNumber -> Classification.ABUNDANT
        aliquotSum == naturalNumber -> Classification.PERFECT
        aliquotSum < naturalNumber -> Classification.DEFICIENT
        else -> throw RuntimeException()
    }
}
