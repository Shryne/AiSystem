package logic.sequence.mill

import base.mill.*
import logic.sequence.GameSequence
import logic.sequence.Player

private typealias MillAiPlayer = Player<ImmutableMillBinary, Move>

class SequenceMillBinary(private val player: MillAiPlayer, private val otherPlayer: MillAiPlayer) : GameSequence {
    private val mill = MillWrapper(mill())


    private val millView = ImmutableMillBinary(mill)
    private var currentPlayer = player

    override fun step(amount: Int) {
        assert(0 < amount)

        (0 until amount).forEach {
            if (mill.isOver) mill.reset()
            mill.moved(currentPlayer.move(millView))
            currentPlayer = currentPlayer.other
        }
    }

    // TODO: Add to interface
    fun game(amount: Int) {
        assert(0 < amount)

        var games = 0
        do {
            if (mill.isOver) mill.reset()

            mill.moved(currentPlayer.move(millView))
            currentPlayer = currentPlayer.other

            if (mill.isOver) games++
        } while (games < amount)
    }

    override fun toString() = mill.toString()
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private val MillAiPlayer.other
        get() =
            if (this == player) otherPlayer
            else player
}
