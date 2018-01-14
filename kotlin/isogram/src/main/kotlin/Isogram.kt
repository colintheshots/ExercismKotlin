class Isogram {
    companion object {
        fun isIsogram(input: String) : Boolean {
            val filtered = input.filterNot { setOf(' ','-').contains(it) }
            return filtered.toLowerCase()
                    .toCharArray().distinct().size == filtered.length
        }
    }
}