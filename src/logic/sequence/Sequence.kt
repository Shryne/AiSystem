package logic.sequence

/**
 * - mutable
 *
 * Holds an amount of Players depending on the underlying game and orders the execution of the players moves. This is
 * important due to different possible implementation of the games and to separate this concern from the environment,
 * this interface exists.
 * @param
 * @return
 */
interface Sequence {
    fun step(amount: Int = 1)
}