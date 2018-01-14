import java.lang.Character.getNumericValue
import java.lang.Character.isDigit

class Luhn {
    companion object {
        fun isValid(input : String) : Boolean {
            return input
                    .replace("\\s".toRegex(), "")
                    .takeIf { it.length > 1 && it.all(::isDigit) }
                    ?.map { getNumericValue(it) }
                    ?.asReversed()
                    ?.mapIndexed {
                        i, num ->
                            if (i % 2 == 1) { if (num * 2 > 9) (num * 2 - 9) else (num * 2) } else num }
                    ?.sum()
                    ?.rem(10) == 0
        }
    }
}