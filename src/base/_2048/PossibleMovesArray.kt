package base._2048

import base.EventualMemoryArray
import java.util.*


/**
 * - mutable
 *
 * @param
 * @return
 */
class PossibleMovesArray : EventualMemoryArray<Move2048> {
    private val container = Array(Move2048.AMOUNT) { Move2048.LEFT } // Dummy value
    override var size = 0
        private set

    override operator fun get(index: Int): Move2048 {
        assert(index in 0 until size)
        return container[index]
    }

    override operator fun set(index: Int, elem: Move2048) {
        assert(index in 0 until Move2048.AMOUNT)
        container[index] = elem
        size = index + 1
    }

    operator fun contains(element: Move2048) = (0 until size).any { container[it] == element }
    override fun toString() = Arrays.toString(Arrays.copyOfRange(container, 0, size))!!
}