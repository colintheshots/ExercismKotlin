object Bob {
    fun hey(input: String) : String {
        return when {
            (input !in Regex("[a-z]")
                    && input in Regex("[A-Z]")) -> "Whoa, chill out!"
            input.trim() in Regex("\\?$") -> "Sure."
            input !in Regex("\\w") -> "Fine. Be that way!"
            else -> "Whatever."
        }
    }
}

private operator fun Regex.contains(input: String): Boolean { return input.contains(this) }