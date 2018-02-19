package logic.sequence.mill

import base.mill.*
import logic.sequence.ImmutableGame

/**
 * - mutable
 *
 * @param
 * @return
 */
class ImmutableMillBinary(private val mill: MillWrapper): ImmutableGame<Long, Move> {
    override val possibleMoves get() = mill.possibleMoves

    override val isOver get() = mill.isOver
    override val map get() = mill.map
}