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
object Moving : Stage {
    override val order = 1

    /**
     * The given move must contain the source and the target stone. The source stone will be removed from the current
     * players board and the target stone will be added. This stage can transition to Moving and Removing1.
     *
     * -> tested <-
     */
    override fun moved(mill: Mill, move: Move): Game {
        val player = mill.player
        val playerBoard = player.board xor move
        val playerNextStage = next(playerBoard, move and playerBoard)

        val otherPlayer = mill.otherPlayer

        return playerNextStage.orderedPlayersGame(
                player(player.num, playerNextStage, 0, playerBoard),
                player(otherPlayer.num, otherPlayer.stage, otherPlayer.stonesToSet, otherPlayer.board)
        )
    }

    override fun moves(mill: Mill, to: EventualMemoryArray<Move>): EventualMemoryArray<Move> {
        val fullBoard = mill.player.board or mill.otherPlayer.board
        var board = mill.player.board
        var i = 0
        while (board != 0) {
            val source = board.rightestBit

            transitions[source]!!.forEach {
                target ->
                if (target and fullBoard == 0) {
                    to[i] = source or target
                    i++
                }
            }

            board = board xor source
        }
        return to
    }


    override fun orderedPlayersGame(player: Player, otherPlayer: Player) = mill(otherPlayer, player)

    override fun toString() = "Moving"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun next(playerBoard: Int, targetMove: Move) =
            when {
                playerBoard.isMill(targetMove) -> Removing1
                else                     -> this
            }
}