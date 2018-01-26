package logic.sequence.mill

import base.EventualMemoryArray
import base.mill.*
import logic.sequence.GameSequence
import logic.sequence.Player

private typealias MillAiPlayer = Player<ImmutableMillBinary, Move>

class SequenceMillBinary(private val player: MillAiPlayer, private val otherPlayer: MillAiPlayer) : GameSequence {
    private var mill: Mill = mill()
    private val millWrapper: ImmutableMillBinary = ImmutableMillBinary()
    private var currentPlayer = player

    override fun step(amount: Int) {
        require(0 < amount)

        (0 until amount).forEach {
            mill = mill.moved(currentPlayer.move(millWrapper))
            currentPlayer = currentPlayer.other
            if (mill.over) mill = mill()
        }
    }

    //override fun toString() = "Player 1: $player\n$game"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private val MillAiPlayer.other
        get() =
            if (this == player) otherPlayer
            else player
}