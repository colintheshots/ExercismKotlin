class Anagram(val target: String) {
    fun match(input: List<String>) : Set<String> {
        return input.filter { s ->
            var charSet : MutableMap<Char, Int?> = target.toLowerCase()
                    .associateBy({it}) { char -> target.toLowerCase().count { it == char }}.toMutableMap()
            val charsMatch = s.toLowerCase().all { char ->
                if (charSet.containsKey(char)) {
                    charSet[char] = charSet[char]?.minus(1)
                    if (charSet[char]==0) charSet.remove(char)
                    return@all true
                } else return@all false
            }
            charsMatch && charSet.isEmpty() && target.toLowerCase()!=s.toLowerCase()
        }.toSet()
    }
}