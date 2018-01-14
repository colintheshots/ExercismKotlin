import java.util.*

object BracketPush {
    private val openCloseMap = mapOf('{' to '}', '(' to ')', '[' to ']')
    fun isValid(input: String) : Boolean {
        val stack = Stack<Char>()
        input.filter { openCloseMap.containsKey(it) || openCloseMap.containsValue(it)}
                .forEach {
                    if (openCloseMap.containsKey(it))
                        stack.push(it)
                    else {
                        try {
                            if (openCloseMap[stack.pop()] != it) return false
                        } catch (e: EmptyStackException) {
                            return false
                        }
                    }
                }
        return stack.isEmpty()
    }
}