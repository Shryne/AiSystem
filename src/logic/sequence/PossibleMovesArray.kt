package logic.sequence

import base.EventualMemoryArray
import java.util.*

/**
 * - mutable
 *
 * @param
 * @return
 */
class PossibleMovesArray(private val amount: Int) : EventualMemoryArray<Int> {
    private val container = IntArray(amount)
    override val empty get() = size == 0
    override var size = 0
        private set

    override fun get(index: Int): Int {
        assert(index in 0 until size)
        return container[index]
    }

    override operator fun set(index: Int, element: Int) {
        assert(index in 0 until amount)
        container[index] = element
        size = index + 1
    }

    override fun clear() {
        size = 0
    }

    override fun iterator(): Iterator<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    operator fun contains(element: Int) = element in container
    override fun toString() = Arrays.toString(Arrays.copyOfRange(container, 0, size))!!
}