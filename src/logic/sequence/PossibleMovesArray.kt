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
    override var size = 0
        private set

    override fun get(index: Int): Int {
        assert(index in 0..size - 1)
        return container[index]
    }

    override operator fun set(index: Int, element: Int) {
        assert(index in 0..amount - 1)
        container[index] = element
        size = index + 1
    }

    operator fun contains(element: Int) = element in container
    override fun toString() = Arrays.toString(Arrays.copyOfRange(container, 0, size))!!
}