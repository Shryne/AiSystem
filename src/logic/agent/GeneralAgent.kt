package logic.agent

import logic.sequence.ImmutablePossibleMovesArray

/**
 * - mutable
 *
 * @param
 * @return
 */
interface GeneralAgent {
    fun move(state: Long, possibleMoves: ImmutablePossibleMovesArray): Int
}