package logic.env.game2048

import base._2048.Game2048Binary
import base._2048.Move2048

interface Simulation2048 : Simulation<Long, Move2048>

/**
 * - mutable
 *
 * @param
 * @return
 */
class Simulation2048Binary : Simulation2048 {
    private val game = Game2048Binary()

    override val isOver get() = game.isOver
    override var map: Long
        get() = game.binaryMap
        set(value) {
            game.binaryMap = value
        }

    override val possibleMoves get() = game.possibleMoves

    override fun simulation(move: Move2048) {
        game.progress(move)
    }

    override fun restart() {
        game.restart()
    }
}