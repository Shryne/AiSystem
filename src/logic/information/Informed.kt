package logic.information

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Informed<T> {
    fun addAll(vararg information: Information<T>)
    fun remove(index: Int)
}