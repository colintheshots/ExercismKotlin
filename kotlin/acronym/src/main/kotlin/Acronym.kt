class Acronym {
    companion object {
        fun generate(phrase: String) : String {
            return phrase.split("\\W".toRegex())
                    .mapNotNull { if (it.isNotEmpty()) it[0] else null }
                    .joinToString("")
                    .toUpperCase()
        }
    }
}