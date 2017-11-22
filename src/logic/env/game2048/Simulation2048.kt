package logic.env.game2048

import base._2048.Game2048Binary
import base._2048.ImmutablePossibleMovesArray
import base._2048.Move2048

/**
 * - mutable
 *
 * @param
 * @return
 */
class Simulation2048Binary : Simulation<Long, Move2048> {
    private val game = Game2048Binary()

    override var startMap: Long
        get() = game.binaryMap
        set(value) {
            game.binaryMap = value
        }

    override fun possibleMoves(map: Long): ImmutablePossibleMovesArray<Move2048> {
        game.binaryMap = map
        return game.possibleMoves
    }

    override fun isOver(map: Long): Boolean {
        game.binaryMap = map
        return game.isOver
    }

    override fun simulation(move: Move2048, map: Long): Long {
        game.progress(move)
        return map
    }
}