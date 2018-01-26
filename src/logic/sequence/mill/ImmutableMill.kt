package logic.sequence.mill

import base.mill.*
import logic.sequence.ImmutableGame

/**
 * - mutable
 *
 * @param
 * @return
 */
class ImmutableMillBinary: ImmutableGame<Long, Move> {
    private val mill: Mill = mill()

    override val possibleMoves = MoveArray()
    override val isOver get() = mill.over
    override val map get() = mill.board
}