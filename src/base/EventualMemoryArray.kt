package base

/**
 * An array that can have a storage or instead a function that defines a value for a certain index.
 */
interface EventualMemoryArray<T> {
    val size: Int

    operator fun set(index: Int, elem: T)
    operator fun get(index: Int): T
}