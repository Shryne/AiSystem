package base.mill.stage

import base.EventualMemoryArray
import base.mill.*
import utils.rightestBit

/**
 *
 * @param
 * @return
 */
object Removing : Stage {
    override val order = 3

    /**
     * The given move must contain the target stone to be removed from the other player.
     * This stage can transition to setting, moving or jumping and the other players stage can be setting, moving
     * or jumping as well.
     *
     * -> tested <-
     */
    override fun moved(mill: Mill, move: Move): Game {
        val player = mill.player
        val stonesToSet = player.stonesToSet
        val board = player.board

        val otherPlayer = mill.otherPlayer
        val otherBoard = otherPlayer.board xor move
        val otherStonesToSet = otherPlayer.stonesToSet

        return mill(
                player(otherPlayer.num, next(otherStonesToSet, otherBoard), otherStonesToSet, otherBoard),
                player(player.num, next(stonesToSet, board), player.stonesToSet, board)
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
        to.clear()
        while (otherBoard != 0) {
            val move = otherBoard.rightestBit
            to[i] = move
            otherBoard = otherBoard xor move
            i++
        }
        return to
    }

    override fun orderedPlayersGame(player: Player, otherPlayer: Player) = mill(player, otherPlayer)

    override fun toString() = "Removing"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun next(stonesToSet: Int, board: Int) =
            when {
                stonesToSet > 0       -> Setting
                board.stoneAmount > 3 -> Moving
                else                  -> Jumping
            }
}