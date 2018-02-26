package logic.sequence.mill

import base.mill.*
import logic.sequence.mill.MillWrapper.Companion.MAX_TURN_AMOUNT

/**
 * - mutable
 *
 * @param
 * @return
 */
class MillWrapperImpl(mill: Mill) : MillWrapper {
    private var turns = 0

    override var mill = mill
        private set

    private val moveArray = MoveArray()
    private var movesLatest = false // to prevent unnecessary move updates
    override val possibleMoves
        get() = moveArray.apply {
            if (!movesLatest) {
                mill.moves(moveArray)
                movesLatest = true
            }
        }

    override val isOver get() = mill.over || turns >= MAX_TURN_AMOUNT || possibleMoves.empty
    override val map get() = mill

    override fun moved(move: Move) {
        require(!mill.over)
        mill = mill.moved(move)
        turns++
        movesLatest = false
    }

    override fun reset() {
        mill = mill()
        turns = 0
        movesLatest = false
    }

    override fun toString() =
            "Current player: ${mill.player.num}\n${mill.asString}" +
                    (if (turns >= MAX_TURN_AMOUNT) "tied" else "") +
                    "\nturns: $turns"
}