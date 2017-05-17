package logic.agent

import logic.sequence.ImmutablePossibleMovesArray
import logic.sequence.Player
import utils.randomFast

/**
 *
 * @param
 * @return
 */
class RandomAgent : Player {
    override fun move(possibleMoves: ImmutablePossibleMovesArray): Int {
        return possibleMoves[randomFast(possibleMoves.size)]
    }

    override fun onGameOver() {}

    override fun toString() = "RandomAgent"
}