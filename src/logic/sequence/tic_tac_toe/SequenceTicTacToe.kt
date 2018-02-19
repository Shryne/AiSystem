package logic.sequence.tic_tac_toe
/*
import base.tic_tac_toe.TicTacToe
import logic.sequence.GameSequence
import logic.sequence.Player

/**
 * - mutable
 *
 * @param
 * @return
 */
class SequenceTicTacToe(private val game: TicTacToe<Int>,
                        val player1: Player<ImmutableTicTacToe, Int>,
                        val player2: Player<ImmutableTicTacToe, Int>): GameSequence {
    private val immutableGame = ImmutableTicTacToe(game)

    override fun step(amount: Int) {
        (0..amount - 1).forEach {
            game.progress(player1.move(immutableGame))
            if (game.isOver) game.newGame()
        }
    }

    override fun toString() = "Player 1: $player1, Player 2: $player2\n$game"
}
        */