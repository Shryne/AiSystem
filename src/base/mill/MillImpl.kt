package base.mill

import java.util.*

/**
 * - mutable
 *
 * @param
 * @return
 */
class MillImpl : MillPlay {
    override val map: IntArray
        get() {
            val result = IntArray(24)
            for (i in 0..23) {
                result[i] = if (player1.stonesSetBoard and (1 shl i) == 1 shl i) 1 else 0
            }
            for (i in 0..23) {
                result[i] = if (player2.stonesSetBoard and (1 shl i) == 1 shl i) 2 else result[i]
            }
            return result
        }

    private val player1 = Player()
    private val player2 = Player()
    override var currentPlayer = player1
        private set

    override val possibleMoves = logic.sequence.PossibleMovesArray(24)

    private val singleLines = intArrayOf(
            // horizontal lines
            0b111_000_000_000_000_000_000_000,
            0b000_111_000_000_000_000_000_000,
            0b000_000_111_000_000_000_000_000,
            0b000_000_000_111_000_000_000_000,
            0b000_000_000_000_111_000_000_000,
            0b000_000_000_000_000_111_000_000,
            0b000_000_000_000_000_000_111_000,
            0b000_000_000_000_000_000_000_111,
            // vertical lines
            0b100_000_000_100_000_000_000_100,
            0b010_010_010_000_000_000_000_000,
            0b001_000_000_000_001_000_000_001,
            0b000_100_000_010_000_000_100_000,
            0b000_001_000_000_010_000_001_000,
            0b000_000_100_001_000_100_000_000,
            0b000_000_001_000_100_001_000_000,
            0b000_000_000_000_000_010_010_010
    )

    private val doubleLines = intArrayOf(
            // doubles (out)
            0b111_000_000_100_000_000_000_100,
            0b111_000_000_000_001_000_000_001,
            0b001_000_000_000_001_000_000_111,
            0b100_000_000_100_000_000_000_111,
            // doubles (middle)
            0b000_111_000_010_000_000_100_000,
            0b000_111_000_000_010_001_000_000,
            0b000_001_000_000_010_000_111_000,
            0b000_100_000_010_000_000_111_000,
            // doubles (inside)
            0b000_000_111_001_000_100_000_000,
            0b000_000_111_000_100_001_000_000,
            0b000_000_000_000_100_111_000_000,
            0b000_000_100_001_000_111_000_000,
            // doubles (rest)
            0b111_010_010_000_000_000_000_000,
            0b010_111_010_000_000_000_000_000,
            0b010_010_111_000_000_000_000_000,
            0b001_000_000_000_111_000_000_001,
            0b000_001_000_000_111_000_001_000,
            0b000_000_001_000_111_001_000_000,
            0b000_000_000_000_000_010_010_111,
            0b000_000_000_000_000_010_111_010,
            0b000_000_000_000_000_111_010_010,
            0b100_000_000_111_000_000_000_100,
            0b000_100_000_111_000_000_100_000,
            0b000_000_100_111_000_100_000_000
    )

    private var stage: Stage = Stage.SETTING
    private var previousStage: Stage = stage

    override fun progress(moveBoard: Int) {
        // TODO: pre checks
        when (stage) {
            Stage.JUMPING,
            Stage.MOVING   -> {
                currentPlayer.stonesSetBoard = currentPlayer.stonesSetBoard or moveBoard
                currentPlayer.toRemove = currentPlayer.millAmount()

                if (currentPlayer.toRemove != 0) {
                    previousStage = stage
                    stage = Stage.REMOVING
                } else currentPlayer = currentPlayer.other()
            }
            Stage.SETTING  -> {
                currentPlayer.stonesSetBoard = currentPlayer.stonesSetBoard or moveBoard
                currentPlayer.toRemove = currentPlayer.millAmount()

                currentPlayer.stonesToSet--
                if (currentPlayer.toRemove != 0) {
                    previousStage = stage
                    stage = Stage.REMOVING
                } else currentPlayer = currentPlayer.other()
            }
            Stage.REMOVING -> {
                currentPlayer.other().stonesSetBoard = currentPlayer.other().stonesSetBoard xor moveBoard
                currentPlayer.toRemove--
                currentPlayer.other().amountOfStones--
                if (currentPlayer.toRemove == 0) {
                    stage = previousStage
                    currentPlayer = currentPlayer.other()
                }
            }
        }

        currentPlayer.other().possibleMovesUpdate()
    }

    override fun reset() {

    }

    override fun toString() =
            "Mill (currentPlayer: $currentPlayer, stage: $stage):\n${Arrays.toString(map)}"
    /*-----------------------------------------------------
    private helper
    -----------------------------------------------------*/
    private fun Player.millAmount(): Int {
        for (it in doubleLines) {
            if (it and stonesSetBoard == it && it and previousMillBoards != it) {
                previousMillBoards = previousMillBoards and it
                return 2
            }
        }

        for (it in singleLines) {
            if (it and stonesSetBoard == it && it and previousMillBoards != it) {
                previousMillBoards = previousMillBoards and it
                return 1
            }
        }
        return 0
    }

    private fun Player.other() =
            when (this) {
                player1 -> player2
                player2 -> player1
                else    -> error("There cannot be another player (arg: $this)")
            }

    private fun Player.possibleMovesUpdate() {
        when (stage) {
            Stage.SETTING -> {
                val empties = (player1.stonesSetBoard or player2.stonesSetBoard).inv()
                for (i in 0..23) {
                    if (empties and (1 shl i) == 1 shl i) {
                        possibleMoves[i] = 1 shl i
                    }
                }
            }
            Stage.REMOVING -> {
                val board = other().stonesSetBoard

            }
        }
    }
}