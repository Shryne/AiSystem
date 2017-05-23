package logic.agent.simulation_game_dependent

import base._2048.Game2048Binary
import base._2048.Move2048
import logic.env.game2048.Simulation

private typealias Simulation2048 = Simulation<Game2048Binary, Move2048>

class Human : SimulationGameAgent<Game2048Binary, Move2048> {
    companion object {
        private const val TRIES_PER_MOVE = 50
    }

    override fun move(simulation: Simulation2048): Move2048 {
        val possibleMoves = simulation.possibleMoves
        for (i in 0..possibleMoves.size - 1) {
            //avgTree(possibleMoves[i], simulation)
        }

        //val resultMap = simulation.possibleMoves
        //        .filter { it != Move2048.UP}.map { Pair(it, avgTree(it)) }

        //if (resultMap.isEmpty()) return Move2048.UP.value
        //else return resultMap.maxBy { it.second }!!.first.value
        return Move2048.DOWN
    }

    /*#####################################################
    private helper
    #####################################################*/
    /*
    private fun avgTree(move: Move2048, simulation: Simulation2048) =
            (0..TRIES_PER_MOVE - 1).fold(0.0) {
                avg, num -> (avg * num + treeBalance(move, simulation)) / (num + 1.0)
            }


    private fun avgTree(move: Move2048): Double =
        (0..TRIES_PER_MOVE - 1).fold(0.0) {
            avg, num -> (avg * num + treeBalance(move)) / (num + 1.0)
        }
*/
    /*
    private fun treeBalance(move: Move2048, simulation: Simulation2048, depth: Int = foresight - 1): Double {
        if (depth < 1) ...
        else {
            simulation.simulation(move)
        }
    }*/

    /*#####################################################
    for settings
    #####################################################*/
    private var foresight = 3

    override fun toString() = "Human"
}