package logic.sequence.game2048

import base._2048.Game2048
import base._2048.Move2048
import logic.sequence.ImmutableGame

/**
 * @param
 * @return
 */
class Immutable2048<out Map>(private val game: Game2048<Map>) : ImmutableGame<Map, Move2048> {
    val highestTile get() = game.highestTile
    val score get() = game.score

    override val map get() = game.map
    override val possibleMoves get() = game.possibleMoves
    override val isOver get() = game.isOver
}