package logic.sequence.mill

import base.mill.Mill
import base.mill.Move
import base.mill.MoveArray

/**
 * - mutable
 *
 * @param
 * @return
 */
interface MillWrapper {
    companion object {
        /**
         * A game that takes this amount of moves is considered to be tied.
         */
        const val MAX_TURN_AMOUNT = 50
    }
    val mill: Mill
    val possibleMoves: MoveArray

    val isOver: Boolean
    val map: Long

    fun moved(move: Move)
    fun reset()
}