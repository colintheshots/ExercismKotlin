import kotlin.properties.Delegates

class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    interface Subscription {
        fun cancel()
    }

    inner class CallbackSubscription(private val cancel: () -> Boolean) : Subscription {
        override fun cancel() {
            cancel.invoke()
        }
    }

    inner abstract class Cell {
        abstract var value : T
        var observers = mutableListOf<ComputeCell>()
    }

    inner class InputCell(initialValue: T) : Cell() {
        override var value : T by Delegates.observable(initialValue) { _, _, _ ->
            observers.forEach {
                it.onNext()
                it.notifyCallbacks()
            }
        }
    }

    inner class ComputeCell(private vararg val inputCells: Cell,
                            private val lambda: ((List<T>) -> T)) : Cell() {

        var callbacks = mutableSetOf<(T)->Boolean>()

        override var value : T by Delegates.observable(compute(inputCells)) { _, _, _ ->
            observers.forEach {
                it.onNext()
            }
        }

        fun onNext() {
            value = compute(inputCells)
        }

        private fun compute(inputCells: Array<out Cell>) : T = lambda.invoke(inputCells.map { it.value })

        init {
            inputCells.forEach { inputCell ->
                inputCell.observers.add(this)
            }
        }

        fun addCallback(lambda: (T) -> Boolean) : Subscription {
            val subscription = CallbackSubscription {callbacks.remove(lambda)}
            callbacks.add(lambda)
            return subscription
        }

        fun notifyCallbacks() = callbacks.forEach { it.invoke(value) }
    }
}
