package logic.env.game2048

import base._2048.ImmutablePossibleMovesArray

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Simulation<Map, Move> {
    var startMap: Map

    fun isOver(map: Map): Boolean
    fun possibleMoves(map: Map): ImmutablePossibleMovesArray<Move>
    fun simulation(move: Move, map: Map): Map
}