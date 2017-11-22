package base.tic_tac_toe

import base._2048.ImmutablePossibleMovesArray
import base._2048.asImmutableArray
import base.tic_tac_toe.TicTacToe.Companion.PLAYER1
import base.tic_tac_toe.TicTacToe.Companion.PLAYER2
import java.util.*

/**
 * - mutable because the map and the player are mutable
 *
 * The Board is modeled by an integer (8 bytes) and 4 1/2 bytes are actually really used.
 * board = 0000.0000.0000.00|00.0000.0000.0000.0000
 *                            8| 7|6| 5|4| 3|2| 1|0
 *
 * 0|1|2
 * 3|4|5
 * 6|7|8
 *
 * @param
 * @return
 */
class TicTacToe(oldGame: TicTacToe? = null) {
    companion object {
        const val NO_PLAYER = " "
        const val PLAYER1 = "X"
        const val PLAYER2 = "O"
        const val DRAW = "DRAW"
    }

    val map: Array<String> = oldGame?.map?.copyOf() ?: Array(9) { NO_PLAYER }
    var currentPlayer: String = oldGame?.currentPlayer ?: PLAYER1
        private set

    val isOver get() = playerWon != NO_PLAYER

    val emptyFieldsAmount get() = map.count { NO_PLAYER == it }

    val playerWon
        get() = if (playerWon(PLAYER1)) PLAYER1
        else if (playerWon(PLAYER2)) PLAYER2
        else if (map.any { it == NO_PLAYER }) NO_PLAYER
        else DRAW

    val possibleMoves: ImmutablePossibleMovesArray<Int>
        get() = ArrayList<Int>().apply {
            map.forEachIndexed {
                index, _ ->
                if (isFieldFree(index)) add(index)
            }
        }.asImmutableArray()

    fun progress(field: Int) {
        assert(field in map.indices && map[field] == NO_PLAYER)

        map[field] = currentPlayer
        currentPlayer = currentPlayer.other
    }

    fun isFieldFree(field: Int) = map[field] == NO_PLAYER

    fun newGame() {
        Arrays.fill(map, NO_PLAYER)
        currentPlayer = PLAYER1
    }

    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun playerWon(player: String) =
            isRowFilled(0, player) || isRowFilled(1, player) || isRowFilled(2, player) ||
                    isColumnFilled(0, player) || isColumnFilled(1, player) || isColumnFilled(2, player) ||
                    (map[4] == player &&
                            ((map[0] == player && map[8] == player) || (map[2] == player && map[6] == player)))

    private fun isRowFilled(row: Int, player: String) =
            map[0 + 3 * row] == player && map[1 + 3 * row] == player && map[2 + 3 * row] == player

    private fun isColumnFilled(column: Int, player: String) =
            map[column] == player && map[column + 3] == player && map[column + 6] == player

    /*------------------------------------------------------------------------------------------------------------------
    overridden
    ------------------------------------------------------------------------------------------------------------------*/
    override fun toString(): String {
        val result = StringBuilder("[playerWon: ").append(playerWon).append("]\n[")

        for (i in map.indices) {
            result.append(map[i])

            if ((i + 1) % 3 == 0) result.append("]\n[")
            else result.append(",")
        }

        return result.deleteCharAt(result.length - 1).toString()
    }
}

val String.other
    get() = when (this) {
        PLAYER1 -> PLAYER2
        PLAYER2 -> PLAYER1
        else    -> error("There is no player $this!")
    }