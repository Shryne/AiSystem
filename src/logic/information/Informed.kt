package logic.information

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Informed<out T> {
    fun addAll(vararg information: Information<T>)
    fun remove(index: Int)
}