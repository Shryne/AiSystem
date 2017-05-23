package logic.env.game2048

import base._2048.ImmutablePossibleMovesArray

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Simulation<Map, Move> {
    var map: Map
    val possibleMoves: ImmutablePossibleMovesArray<Move>
    val isOver: Boolean

    fun simulation(move: Move)
    fun restart()
}