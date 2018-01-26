package logic.agent

import base.EventualMemoryArray


/**
 * - mutable
 *
 * @param
 * @return
 */
interface GeneralAgent<M> {
    fun move(state: Long, possibleMoves: EventualMemoryArray<M>): M
}