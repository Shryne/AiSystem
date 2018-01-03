package base.mill.stage

import base.EventualMemoryArray
import base.mill.*
import utils.rightestBit

object Setting : Stage {
    override val order = 0

    /**
     * Sets the stone from move on the first player of mill and reduces the stonesToSet variable inside
     * mill by one. This stage can lead to the moving stage, if stonesToSet reaches zero, or to the Removing1/2 Stage,
     * if a mill or doubleMill is created with the given move.
     *
     * @return The resulting game
     * * -> tested <-
     */
    override fun moved(mill: Mill, move: Move): Game {
        val player = mill.player
        val playerBoard = player.board or move
        val stonesToSet = player.stonesToSet - 1
        val playerNextStage = next(playerBoard, stonesToSet, move)

        val otherPlayer = mill.otherPlayer

        return playerNextStage.orderedPlayersGame(
                player(player.num, playerNextStage, stonesToSet, playerBoard),
                player(otherPlayer.num, otherPlayer.stage, otherPlayer.stonesToSet, otherPlayer.board)
        )
    }

    /**
     * One move for every free field on the board.
     *
     * -> tested <-
     */
    override fun moves(mill: Mill, to: EventualMemoryArray<Move>): EventualMemoryArray<Move> {
        var board = (mill.player.board or mill.otherPlayer.board).inv() and 0xFF_FF_FF // TODO: Should be a constant
        var i = 0
        while (board != 0) {
            val move = board.rightestBit
            to[i] = move
            board = board xor move
            i++
        }
        return to
    }


    override fun orderedPlayersGame(player: Player, otherPlayer: Player) = mill(otherPlayer, player)

    override fun toString() = "Setting"
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun next(playerBoard: Int, stonesToSet: Int, move: Move) =
            if (playerBoard.isMill(move))
                if (playerBoard.isDoubleMill(move)) Removing2
                else Removing1
            else if (stonesToSet == 0) Moving
            else this
}