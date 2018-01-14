class ForthEvaluator {
    private val userDefinedMap = mutableMapOf<String, List<Token>>()

    fun evaluateProgram(program: List<String>) : List<Int> {
        val tokenLines : List<List<Token>> = program.map { line ->
            val tokens = line.split("\\s".toRegex())
            tokens.map { token ->
                when (token) {
                    in Regex("^\\d+$") -> Num(Integer.parseInt(token))
                    in Regex("[+]") -> Operation(Op.ADD)
                    in Regex("[-]") -> Operation(Op.SUBTRACT)
                    in Regex("[*]") -> Operation(Op.MULTIPLY)
                    in Regex("[/]") -> Operation(Op.DIVIDE)
                    in Regex("DUP", RegexOption.IGNORE_CASE) -> Operation(Op.DUP)
                    in Regex("DROP", RegexOption.IGNORE_CASE) -> Operation(Op.DROP)
                    in Regex("SWAP", RegexOption.IGNORE_CASE) -> Operation(Op.SWAP)
                    in Regex("OVER", RegexOption.IGNORE_CASE) -> Operation(Op.OVER)
                    in Regex(";") -> Operation(Op.DEFINE)
                    else -> Word(token)
                }
            }
        }
        return tokenLines.map { tokenLine ->
            val expression = mutableListOf<Token>()

            tokenLine.forEach { token ->
                expression.add(token)
            }
            evalExpression(expression)
        }.flatMap { x -> x }
    }

    private fun evalExpression(expression: List<Token>): List<Int> {
        return when {
            expression.isEmpty() -> emptyList()
            expression.last() is Word -> {
                val word = (expression.last() as Word).word
                if (userDefinedMap.containsKey(word)) {
                    userDefinedMap[word]?.let { evalExpression(expression.dropLast(1).plus(it)) } ?: emptyList()
                }
                else
                    throw IllegalArgumentException("No definition available for operator \"$word\"")
            }
            expression.last() is Operation -> {
                val op = expression.last() as Operation
                val operands = expression.dropLast(1)
                if (userDefinedMap.containsKey(op.operation.name)) {
                    userDefinedMap[op.operation.name]?.let { evalExpression(expression.dropLast(1).plus(it)) } ?: emptyList()
                } else when (op.operation) {
                    Op.ADD -> {
                        require(operands.size > 1) {"Addition requires that the stack contain at least 2 values"}
                        val second = (operands.last() as Num).number
                        val first = operands.dropLast(1)
                        val firstNum = if (first.last() is Num) {
                            first.last().toInt()
                        } else {
                            evalExpression(first)[0]
                        }
                        listOf(firstNum + second)
                    }
                    Op.SUBTRACT -> {
                        require(operands.size > 1) {"Subtraction requires that the stack contain at least 2 values"}
                        val second = (operands.last() as Num).number
                        val first = operands.dropLast(1)
                        val firstNum = if (first.last() is Num) {
                            first.last().toInt()
                        } else {
                            evalExpression(first)[0]
                        }
                        listOf(firstNum - second)
                    }
                    Op.MULTIPLY -> {
                        require(operands.size > 1) {"Multiplication requires that the stack contain at least 2 values"}
                        val second = (operands.last() as Num).number
                        val first = operands.dropLast(1)
                        val firstNum = if (first.last() is Num) {
                            first.last().toInt()
                        } else {
                            evalExpression(first)[0]
                        }
                        listOf(firstNum * second)
                    }
                    Op.DIVIDE -> {
                        require(operands.size > 1) {"Division requires that the stack contain at least 2 values"}
                        val second = (operands.last() as Num).number
                        val first = operands.dropLast(1)
                        val firstNum = if (first.last() is Num) {
                            first.last().toInt()
                        } else {
                            evalExpression(first)[0]
                        }
                        require(second != 0) {"Division by 0 is not allowed"}
                        listOf(firstNum / second)
                    }
                    Op.DUP -> {
                        require(operands.isNotEmpty()) {"Duplicating requires that the stack contain at least 1 value"}
                        val value = operands.last()
                        val dupedNum = (value as? Num)?.number ?: (evalExpression(operands))[0]
                        evalExpression(operands.dropLast(1)) + listOf(dupedNum, dupedNum)
                    }
                    Op.DROP -> {
                        require(operands.isNotEmpty()) {"Dropping requires that the stack contain at least 1 value"}
                        evalExpression(operands.dropLast(1))
                    }
                    Op.SWAP -> {
                        require(operands.size > 1) {"Swapping requires that the stack contain at least 2 values"}
                        evalExpression(operands.dropLast(2).plus(operands.takeLast(2).reversed()))
                    }
                    Op.OVER -> {
                        require(operands.size > 1) {"Overing requires that the stack contain at least 2 values"}
                        evalExpression(operands.plus(operands[operands.size - 2]))
                    }
                    Op.DEFINE -> {
                        require(operands.size > 2)
                        require(operands[1] !is Num) {"Cannot redefine numbers"}
                        val name = when (operands[1]) {
                            is Word -> (operands[1] as Word).word
                            is Operation -> (operands[1] as Operation).operation.name
                            else -> throw IllegalArgumentException("Illegal user defined type")
                        }
                        userDefinedMap.put(name, operands.drop(2))
                        emptyList()

                    }
                }
            }
            expression.all { it is Num } -> expression.map { number ->
                number.toInt()
            }
            else -> throw IllegalArgumentException()
        }
    }
}

sealed class Token
class Num(val number: Int) : Token()
class Operation(val operation: Op) : Token()
class Word(val word: String) : Token()
enum class Op {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, DUP, DROP, SWAP, OVER, DEFINE
}

fun Token.toInt(): Int = (this as Num).number
operator fun Regex.contains(text: String) : Boolean = this.matches(text)