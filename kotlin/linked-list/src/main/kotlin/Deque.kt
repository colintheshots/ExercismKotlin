class Deque<T> {

    data class Node<S>(var value: S?, var next: Node<S>?, var prev: Node<S>?)

    var head = Node<T?>(null, null, null)
    var tail = head

    fun push(value: T) {
        if (tail.value == null) tail.value = value
        else {
            val newTail = Node(value, null, tail)
            tail.next = newTail
            tail = newTail
        }
    }

    fun pop() : T? {
        val result = tail.value
        val newTail = tail.prev ?: head
        newTail.next = null
        tail = newTail
        return result
    }

    fun shift() : T? {
        val result = head.value
        if (head.next == null) head = tail
        else {
            val newHead = head.next
            newHead?.prev = null
            head = newHead!!
        }
        return result
    }

    fun unshift(value: T) {
        if (head.value == null) head.value = value
        else {
            val newHead = Node(value, head, null)
            head = newHead
        }
    }
}