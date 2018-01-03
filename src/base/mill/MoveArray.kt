package base.mill

import base.EventualMemoryArray
import java.util.*

/**
 * - mutable
 *
 * @param
 * @return
 */
class MoveArray(elems: IntArray = IntArray(0)) : EventualMemoryArray<Move> {
    companion object {
        /**
         * Based on:
         * 18 move possibilities per stone if both players are in the jumping stage -> 54
         */
        private const val MAX_MOVE_AMOUNT = 54
    }

    private val container: IntArray = IntArray(MAX_MOVE_AMOUNT)

    init {
        require((Move == Int) and (elems.size <= MAX_MOVE_AMOUNT))
        for (i in 0..(elems.size - 1)) {
            container[i] = elems[i]
        }
    }

    override var size: Int = 0

    override fun set(index: Int, elem: Int) {
        assert((0 <= index) and (index < MAX_MOVE_AMOUNT)) {
            println("Index ($index) must be between 0 and $MAX_MOVE_AMOUNT")
        }
        container[index] = elem
        size = index + 1
    }

    override fun get(index: Int): Int {
        assert((0 <= index) and (index < size))
        return container[index]
    }

    // TODO: Better string representation
    override fun toString() = "MoveArray: ${Arrays.toString(container)}"

    override fun equals(other: Any?) =
            if (other is MoveArray) { // TODO: Not nice to use the implementation class
                container.toSet() == other.container.toSet()
            } else false

    override fun hashCode() = Arrays.hashCode(container)
}