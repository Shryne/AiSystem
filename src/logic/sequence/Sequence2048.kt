package logic.sequence

import base._2048.Game2048
import base._2048.ImmutablePossibleMovesArray
import base._2048.Move2048
import logic.sequence.game2048.Immutable2048

/**
 * - mutable
 *
 * @param
 * @return
 */
class Sequence2048(private val game: Game2048<*>, val player: Player<Immutable2048<*>, Move2048>) : GameSequence {
    private val immutableGame = Immutable2048(game)

    override fun step(amount: Int) =
        (0..amount - 1).forEach {
            game.progress(player.move(immutableGame))
            if (game.isOver) game.restart()
        }

    override fun toString() = "Player 1: $player\n$game"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun PossibleMovesArray.synchronize(with: ImmutablePossibleMovesArray<Move2048>) {
        for (i in 0..with.size - 1) {
            this[i] = with[i].toInt()
        }
    }

    private fun Move2048.toInt() =
            when (this) {
                Move2048.LEFT  -> 0
                Move2048.RIGHT -> 1
                Move2048.UP    -> 2
                Move2048.DOWN  -> 3
            }

    private fun Int.toMove() =
            when (this) {
                0    -> Move2048.LEFT
                1    -> Move2048.RIGHT
                2    -> Move2048.UP
                3    -> Move2048.DOWN
                else -> throw IllegalArgumentException("Move has to be between 0 and 3 but is $this")
            }
}