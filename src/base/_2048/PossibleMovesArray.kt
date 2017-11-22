package base._2048

import java.util.*

interface ImmutablePossibleMovesArray<out T> {
    val size: Int
    operator fun get(index: Int): T
}
fun <E> List<E>.asImmutableArray() =
        object : ImmutablePossibleMovesArray<E> {
            override val size get() = this@asImmutableArray.size

            override fun get(index: Int): E = this@asImmutableArray[index]
        }

/**
 * - mutable
 *
 * @param
 * @return
 */
class PossibleMovesArray : ImmutablePossibleMovesArray<Move2048> {
    private val container = Array(Move2048.AMOUNT) { Move2048.LEFT } // Dummy value
    override var size = 0
        private set

    override operator fun get(index: Int): Move2048 {
        assert(index in 0..size - 1)
        return container[index]
    }

    operator fun set(index: Int, element: Move2048) {
        assert(index in 0..Move2048.AMOUNT - 1)
        container[index] = element
        size = index + 1
    }

    operator fun contains(element: Move2048) = (0..size - 1).any { container[it] == element }
    override fun toString() = Arrays.toString(Arrays.copyOfRange(container, 0, size))!!
}