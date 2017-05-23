package logic.env.game2048

import base._2048.ImmutablePossibleMovesArray
import base._2048.Move2048
import logic.agent.GeneralAgent
import logic.sequence.Player
import logic.sequence.game2048.Immutable2048

/**
 * - mutable
 *
 * @param
 * @return
 */
class Environment2048(private val generalAgent: GeneralAgent<Move2048>) : Player<Immutable2048<Long>, Move2048> {
    override fun move(immutableGame: Immutable2048<Long>) =
            generalAgent.move(immutableGame.map, immutableGame.possibleMoves)

}