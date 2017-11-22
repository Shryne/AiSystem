package logic.sequence.tic_tac_toe

import base.tic_tac_toe.TicTacToe
import logic.sequence.ImmutableGame

/**
 * - mutable
 *
 * @param
 * @return
 */
class ImmutableTicTacToe(private val game: TicTacToe) : ImmutableGame<Array<String>, Int> {
    override val possibleMoves get() = game.possibleMoves
    override val isOver get() = game.isOver
    override val map get() = game.map

}