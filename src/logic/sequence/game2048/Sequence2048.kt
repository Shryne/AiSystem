package logic.sequence.game2048

import base._2048.Game2048
import base._2048.ImmutablePossibleMovesArray
import base._2048.Move2048
import logic.sequence.GameSequence
import logic.sequence.Player
import logic.sequence.PossibleMovesArray

/**
 * - mutable
 *
 * @param
 * @return
 */
class Sequence2048<in Map>(private val game: Game2048<Map>, val player: Player<Immutable2048<Map>, Move2048>) : GameSequence {
    private val immutableGame = Immutable2048(game)

    override fun step(amount: Int) =
        (0..amount - 1).forEach {
            game.progress(player.move(immutableGame))
            if (game.isOver) game.restart()
        }

    override fun toString() = "Player 1: $player\n$game"
}