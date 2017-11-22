package logic.agent.simulation_game_dependent

import base._2048.Move2048
import logic.env.game2048.Simulation

private typealias Simulation2048 = Simulation<Long, Move2048>

class Human : SimulationGameAgent<Long, Move2048> {
    companion object {
        private const val TRIES_PER_MOVE = 50
    }

    override fun move(simulation: Simulation2048): Move2048 {
        val possibleMoves = simulation.possibleMoves(simulation.startMap)
        //println(possibleMoves)
        var bestMove = possibleMoves[0]
        var bestMoveValue = 0.0
        for (i in 0..possibleMoves.size - 1) {
            avgTree(possibleMoves[i], simulation).apply {
                if (this > bestMoveValue) {
                    bestMove = possibleMoves[i]
                    bestMoveValue = this
                }
            }
        }

        return bestMove
    }

    /*#####################################################
    private helper
    #####################################################*/

    private fun avgTree(move: Move2048, simulation: Simulation2048) =
            (0..TRIES_PER_MOVE - 1).fold(0.0) {
                avg, num -> (avg * num + treeBalance(move, simulation)) / (num + 1.0)
            }

    private fun treeBalance(move: Move2048, simulation: Simulation2048, map: Long = simulation.startMap,
                            depth: Int = foresight - 1): Double =
        if (depth < 1) value(map)
        else {
            val resultMap = simulation.simulation(move, map)
            val resultMoves = simulation.possibleMoves(resultMap)

            (0..resultMoves.size - 1).sumByDouble {
                treeBalance(resultMoves[it], simulation, resultMap, depth - 1)
            }
        }

    private fun value(map: Long): Double {
        return BoardOrdering.upLeftOrder(map).ushr(1).toDouble()
    }

    /*#####################################################
    for settings
    #####################################################*/
    private var foresight = 1

    override fun toString() = "Human"
}