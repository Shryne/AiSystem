package logic.information

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Information<in T> {
    val name: String
    val value: String

    fun update(event: Event, basedOn: T)
}