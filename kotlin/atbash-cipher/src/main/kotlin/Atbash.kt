object Atbash {
    private val chars = ('a'..'z')
    private val atbash = { str : String ->
        str.mapNotNull {
            when {
                it.isLetter() -> chars.reversed().elementAt(chars.indexOf(it))
                it.isDigit() -> it
                else -> null
            }
        }
    }

    fun encode(plaintext: String) : String {
        return atbash.invoke(plaintext.toLowerCase())
                .chunked(5) { it.joinToString("")}
                .joinToString(" ")
    }

    fun decode(codetext: String) : String {
        return atbash.invoke(codetext).joinToString("")
    }
}