package logic.env.game2048

import base.EventualMemoryArray

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Simulation<Map, Move> {
    var startMap: Map

    fun isOver(map: Map): Boolean
    fun possibleMoves(map: Map): EventualMemoryArray<Move>
    fun simulation(move: Move, map: Map): Map
}