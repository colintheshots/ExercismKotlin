import java.security.SecureRandom

class Robot {
    var name : String = generateName()

    fun reset() {
        name = generateName()
    }

    private fun generateName() : String {
        return (0..1).map { (SecureRandom().nextInt(26)).charRepresentation() }.joinToString("")
                .plus((2..4).map { (SecureRandom().nextInt(10)) }.joinToString(""))
    }
}

fun Int.charRepresentation() : Char {
    return 'A'.plus(this)
}