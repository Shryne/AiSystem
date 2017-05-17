package logic.information

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Information<T> {
    val name: String
    val value: String

    fun update(event: Event, basedOn: T)
}