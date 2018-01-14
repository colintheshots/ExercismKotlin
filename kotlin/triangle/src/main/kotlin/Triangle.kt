class Triangle(s1: Number, s2: Number, s3: Number) {
    val isEquilateral : Boolean
    val isIsosceles : Boolean
    val isScalene : Boolean

    init {
        val sortedList = listOf(s1, s2, s3).map{ it.toFloat()}.sorted()
        require(sortedList.none { it <= 0F }) {"sides must have size > 0"}
        require(sortedList.last() <= sortedList.slice(0..1).sum()) {"violates triangle inequality"}
        val sizes = setOf(s1, s2, s3).filter { it != 0 }.count()
        when (sizes) {
            1 -> {isEquilateral = true; isIsosceles = true; isScalene = false}
            2 -> {isEquilateral = false; isIsosceles = true; isScalene = false}
            3 -> {isEquilateral = false; isIsosceles = false; isScalene = true}
            else -> throw IllegalArgumentException()
        }
    }
}
