package logic.sequence

import base._2048.ImmutablePossibleMovesArray

/**
 * @param
 * @return
 */
interface ImmutableGame<out Map, Move> {
    val possibleMoves: ImmutablePossibleMovesArray<Move>
    val isOver: Boolean
    val map: Map
}