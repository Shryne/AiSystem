package logic.sequence.mill

import base.mill.*

/**
 * - mutable
 *
 * @param
 * @return
 */
class MillWrapper(mill: Mill) {
    companion object {
        private const val MAX_TURN_AMOUNT = 500
    }

    private var turns = 0

    var mill = mill
        private set

    private val moveArray = MoveArray()
    val possibleMoves
        get() = moveArray.apply {
            mill.moves(moveArray)
        }

    val isOver get() = mill.over || turns >= MAX_TURN_AMOUNT
    val map get() = mill.board

    fun moved(move: Move) {
        require(!mill.over)
        mill = mill.moved(move)
        turns++
    }

    fun reset() {
        mill = mill()
        turns = 0
    }

    override fun toString() =
            "Current player: ${mill.player.num}\n${mill.asString}" +
                    (if (turns >= MAX_TURN_AMOUNT) "tied" else "") +
                    "\nturns: $turns"
}