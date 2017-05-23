package logic.agent

import base._2048.ImmutablePossibleMovesArray


/**
 * - mutable
 *
 * @param
 * @return
 */
interface GeneralAgent<M> {
    fun move(state: Long, possibleMoves: ImmutablePossibleMovesArray<M>): M
}