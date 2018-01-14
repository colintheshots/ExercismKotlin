import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class BankAccount {
    var balanceInternal = AtomicInteger(0)
    var closedAccount = AtomicBoolean(false)
    val balance : Int
        get() {
            if (closedAccount.get()) throw IllegalStateException()
            else return balanceInternal.get()
        }

    fun adjustBalance(adjustment: Int) {
        if (closedAccount.get()) throw IllegalStateException()
        balanceInternal.getAndAdd(adjustment)
    }

    fun close() {
        closedAccount.set(true)
    }
}