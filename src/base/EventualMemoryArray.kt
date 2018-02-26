package base

/**
 * An array that can have a storage or instead a function that defines a value for a certain index.
 */
interface EventualMemoryArray<T> : Iterable<T> {
    val size: Int
    val empty: Boolean

    operator fun set(index: Int, elem: T)
    operator fun get(index: Int): T
    fun clear()
}