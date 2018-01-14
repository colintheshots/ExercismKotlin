import java.util.*

class Cipher(var key: String = "!EMPTY!") {
    init {
        require(key.isNotEmpty())
        if (key == "!EMPTY!") {
            key = (1..100).map {
                ('a'.toByte() + Random().nextInt(26)).toChar()
            }.joinToString("")
        }
        require(key.all { it in 'a'..'z' })
    }

    fun encode(input: String) : String {
        return input.mapIndexed { index, c ->
            c.toKeyed(key[index])
        }.joinToString("")
    }

    fun decode(input: String) : String {
        return input.mapIndexed { index, c ->
            c.toUnKeyed(key[index])
        }.joinToString("")
    }
}

fun Char.toKeyed(keyChar: Char) : Char {
    return ('a'.toByte() + ((this.toDistance() + keyChar.toDistance()) % 26)).toChar()
}

fun Char.toUnKeyed(keyChar: Char) : Char {
    val distance = this.toDistance() - keyChar.toDistance()
    return if (distance < 0) ('z'.toByte() + distance + 1).toChar() else ('a'.toByte() + distance).toChar()
}

fun Char.toDistance() : Int {
    return this.toByte() - 'a'.toByte()
}