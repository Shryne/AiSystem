package base.tic_tac_toe

/**
 * - mutable
 *
 * @param
 * @return
 */
interface TicTacToe <in M> {
    val over: Boolean

    fun move(move: M)
    fun reset()
}