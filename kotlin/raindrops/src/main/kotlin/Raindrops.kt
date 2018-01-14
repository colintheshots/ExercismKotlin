class Raindrops {
    companion object {
        private val drops = mapOf(3 to "Pling", 5 to "Plang", 7 to "Plong")
        fun convert(value: Int) : String {
            val words = drops.mapNotNull { if (value % it.key == 0) it.value else null }.joinToString("")
            return if (words.isEmpty()) value.toString() else words
        }
    }
}