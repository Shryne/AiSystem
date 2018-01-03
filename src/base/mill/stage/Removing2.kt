package base.mill.stage

import base.EventualMemoryArray
import base.mill.*
import utils.rightestBit

/**
 * - mutable
 *
 * @param
 * @return
 */
object Removing2 : Stage {
    override val order = 4

    /**
     * The given move must contain the target stone to be removed from the other player.
     * This stage can only transition to removing 1 and the other player will keep his stage.
     *
     * -> tested <-
     */
    override fun moved(mill: Mill, move: Move): Game {
        val player = mill.player
        val otherPlayer = mill.otherPlayer

        return mill(
                player(player.num, Removing1, player.stonesToSet, player.board),
                player(otherPlayer.num, otherPlayer.stage, otherPlayer.stonesToSet, otherPlayer.board xor move)
        )
    }

    /**
     * One move for every stone on the opponents board.
     *
     * -> tested <-
     */
    override fun moves(mill: Mill, to: EventualMemoryArray<Move>): EventualMemoryArray<Move> {
        var otherBoard = mill.otherPlayer.board
        var i = 0
        while (otherBoard != 0) {
            val move = otherBoard.rightestBit
            to[i] = move
            otherBoard = otherBoard xor move
            i++
        }
        return to
    }

    override fun orderedPlayersGame(player: Player, otherPlayer: Player) = mill(player, otherPlayer)

    override fun toString() = "Removing2"
}