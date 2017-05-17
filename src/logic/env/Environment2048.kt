package logic.env

import logic.agent.GeneralAgent
import logic.sequence.ImmutablePossibleMovesArray
import logic.sequence.Player

/**
 * - mutable
 *
 * @param
 * @return
 */
class Environment2048(private val generalAgent: GeneralAgent) : Player {
    override fun move(possibleMoves: ImmutablePossibleMovesArray): Int {
        return generalAgent.move(0, possibleMoves)
    }

    override fun onGameOver() {}
}