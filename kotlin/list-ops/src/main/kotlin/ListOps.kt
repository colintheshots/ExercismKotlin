fun <T> List<T>.customAppend(list: List<T>) : List<T> {
    val newList = toMutableList()
    newList.addAll(list)
    return newList
}

fun <T> List<List<T>>.customConcat() = flatMap { it }

fun <T : Any> List<T>.customFilter(lambda: (T) -> Boolean) : List<T> {
    return mapNotNull {
        if (lambda.invoke(it)) it else null
    }
}

val <T> List<T>.customSize : Int
    get() {
        var count = 0
        forEach { count++ }
        return count
    }

fun <T,U> List<T>.customMap(lambda: (T) -> U) : List<U> {
    val newList = mutableListOf<U>()
    forEach {
        newList.add(lambda.invoke(it))
    }
    return newList
}

@Suppress("UNCHECKED_CAST")
fun <S, T> List<S>.customFoldLeft(initial: T, lambda: (T, T) -> T) : T {
    var result = initial
    forEach {
        val element = it as T
        result = lambda.invoke(result, element)
    }
    return result
}

fun <T> List<T>.customFoldRight(initial: T, lambda: (T, T) -> T) : T {
    var result = initial
    customReverse().forEach {
        result = lambda.invoke(it, result)
    }
    return result
}

fun <T> List<T>.customReverse() : List<T> {
    return mapIndexed { index, _ ->  this[size - 1 - index] }
}