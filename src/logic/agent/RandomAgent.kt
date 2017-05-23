package logic.agent

import base._2048.ImmutablePossibleMovesArray
import logic.sequence.ImmutableGame
import logic.sequence.Player
import utils.randomFast

/**
 *
 * @param
 * @return
 */
class RandomAgent<Move> : Player<ImmutableGame<*, Move>, Move> {
    override fun move(immutableGame: ImmutableGame<*, Move>) =
        immutableGame.possibleMoves.run {
            this[randomFast(size)]
        }

    override fun toString() = "RandomAgent"
}