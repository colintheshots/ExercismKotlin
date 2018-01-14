class NumberSpeller {
    fun say(input: Int) : String {
        require(input >= 0) {"Input must be non-negative"}
        return when (input) {
            in 0..9 -> sayDigit(input)
            10 -> "ten"
            11 -> "eleven"
            12 -> "twelve"
            in 13..19 -> "${sayShortly(input % 10)}teen"
            in 20..29 -> input.hyphenate("twenty")
            in 30..39 -> input.hyphenate("thirty")
            in 40..49 -> input.hyphenate("forty")
            in 50..59 -> input.hyphenate("fifty")
            in 60..69 -> input.hyphenate("sixty")
            in 70..79 -> input.hyphenate("seventy")
            in 80..89 -> input.hyphenate("eighty")
            in 90..99 -> input.hyphenate("ninety")
            in 100..999 -> "${sayDigit(input / 100)} hundred${say(input % 100).noZero()}"
            in 1000..999999 -> "${say(input / 1000)} thousand${say(input % 1000).noZero()}"
            in 1000000..999999999 -> "${say(input / 1000000)} million${say(input % 1000000).noZero()}"
            in 1000000000..Int.MAX_VALUE -> "${say(input / 1000000000)} billion${say(input % 1000000000).noZero()}"
            else -> "not implemented"
        }
    }

    fun say(input: Long) : String {
        require(input >= 0L) {"Input must be non-negative"}
        require(input < 1000000000000L) {"Input must be less than 1000000000000"}
        return when (input) {
            in 0L..1000000000L -> say(input.toInt())
            in 1000000000L..999999999999L -> "${say(input / 1000000000)} billion${say(input % 1000000000).noZero()}"
            else -> "not implemented"
        }
    }

    private fun sayDigit(input: Int) : String {
        return when (input) {
            0 -> "zero"
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            5 -> "five"
            6 -> "six"
            7 -> "seven"
            8 -> "eight"
            9 -> "nine"
            else -> "not implemented"
        }
    }

    private fun sayShortly(input: Int) : String {
        return when (input % 10) {
            3 -> "thir"
            5 -> "fif"
            8 -> "eigh"
            else -> sayDigit(input % 10)
        }
    }

    private fun Int.hyphenate(base: String) : String {
        return if (this % 10 == 0) base else "$base-${sayDigit(this % 10)}"
    }

    private fun String.noZero() : String {
        return if (this == "zero") "" else " $this"
    }
}