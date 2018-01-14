import java.util.*

object PigLatin {
    private val vowels = setOf('a','e','i','o','u')
    fun translate(input: String) : String {
        require(input.all { it.isLowerCase() || it.isWhitespace() })
        return input.replace("\\b\\w+\\b".toRegex()) { result : MatchResult ->
            val word = result.value
            when {
                word[0] in vowels -> word + "ay"
                word.startsWith("qu") -> word.substring(2) + "quay"
                word.startsWith("squ") -> word.substring(3) + "squay"
                word == "xray" -> word + "ay"
                else -> {
                    val firstY = word.indexOfFirst { it == 'y' }
                    val firstVowel = word.indexOfFirst { it in vowels }
                    val beforeVowel = firstVowel == -1 || firstY < firstVowel
                    val yIsVowel = firstY != -1 && (word.length == firstY + 1
                            || word.length > firstY + 1
                            && word[firstY + 1] !in vowels)
                    if (beforeVowel && yIsVowel) {
                        val beforeY = word.substring(0, firstY)
                        val afterY = word.substring(firstY)
                        afterY + beforeY + "ay"
                    } else {
                        val beginning = if (firstVowel != -1) word.substring(firstVowel) else word
                        val remainder = if (firstVowel != -1) word.substring(0, firstVowel) else ""
                        beginning + remainder + "ay"
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val numTestCases = scanner.nextInt()
    require(numTestCases in 1..20)
    (0 until numTestCases).map {
        val numThieves = scanner.nextInt()
        require(numThieves in 1..100)

        val duration = scanner.nextInt()
        require(duration in 0..1000000)

        val thiefSlots = mutableListOf(0, 0)
        val entries = (0 until numThieves).map { scanner.nextInt() }
        entries.forEach{ require(it in 0..100)}
        entries.forEach{
            thiefSlots.addToSmallest(it)
        }
        if (thiefSlots.max()!! > duration) {
            "NO"
        } else {
            "YES"
        }
    }.forEach(::println)

}

fun MutableList<Int>.addToSmallest(duration : Int) : MutableList<Int> {
    val indexOfSmallest = indexOfFirst { it == min() }
    this[indexOfSmallest] += duration
    return this
}