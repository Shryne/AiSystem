package base.mill.stage

import base.EventualMemoryArray
import base.mill.*
import utils.rightestBit

object Jumping: Stage {
    override val order = 2

    /**
     * The given move must contain the source and the target stone. The source stone will be removed from the current
     * players board and the target stone will be added. This stage can transition to Jumping or Removing1.
     *
     * -> tested <-
     */
    override fun moved(mill: Mill, move: Move): Game {
        val player = mill.player
        val playerBoard = player.board xor move
        val playerNextStage = next(playerBoard, playerBoard and move)

        val otherPlayer = mill.otherPlayer

        return playerNextStage.orderedPlayersGame(
                player(player.num, playerNextStage, 0, playerBoard),
                player(otherPlayer.num, otherPlayer.stage, otherPlayer.stonesToSet, otherPlayer.board)
        )
    }

    /**
     * Moves: currentPlayer.stones * fullBoard.emptyFields
     * -> tested <-
     */
    override fun moves(mill: Mill, to: EventualMemoryArray<Move>): EventualMemoryArray<Move> {
        val fullBoardFree = (mill.player.board or mill.otherPlayer.board).inv() and 0xFF_FF_FF
        var board = mill.player.board
        var i = 0
        while (board != 0) {
            val source = board.rightestBit
            var tempFullBoardFree = fullBoardFree

            while (tempFullBoardFree != 0) {
                val target = tempFullBoardFree.rightestBit
                to[i] = source or target
                tempFullBoardFree = tempFullBoardFree xor target
                i++
            }

            board = board xor source
        }
        return to
    }


    override fun orderedPlayersGame(player: Player, otherPlayer: Player) = mill(otherPlayer, player)

    override fun toString() = "Jumping"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun next(playerBoard: Int, targetMove: Move) =
            when {
                playerBoard.isMill(targetMove) -> Removing1
                else                     -> this
            }
}