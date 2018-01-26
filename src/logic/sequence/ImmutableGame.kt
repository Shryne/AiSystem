package logic.sequence

import base.EventualMemoryArray

/**
 * @param
 * @return
 */
interface ImmutableGame<out Map, Move> {
    val possibleMoves: EventualMemoryArray<Move>
    val isOver: Boolean
    val map: Map
}