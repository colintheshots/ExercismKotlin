class RotationalCipher(private val distance: Int) {
    fun encode(input: String) : String {
        return input.map {
            when {
                it.isUpperCase() -> ((it.toByte() - 'A'.toByte() + distance) % 26 + 'A'.toByte()).toChar()
                it.isLowerCase() -> ((it.toByte() - 'a'.toByte() + distance) % 26 + 'a'.toByte()).toChar()
                else -> it
            }
        }.joinToString("")
    }
}