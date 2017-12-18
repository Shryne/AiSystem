package base.mill

import logic.sequence.PossibleMovesArray

class Player {
    var stonesToSet = 9
    var amountOfStones = 9
    var board = 0
    var stage = Stage.SETTING
    var previousStage = stage

    private val moves: PossibleMovesArray = PossibleMovesArray(24)

    private val movesSetting: PossibleMovesArray = PossibleMovesArray(24)
    private val movesJumpMove: PossibleMovesArray = PossibleMovesArray(24)
    private val movesRemoving: PossibleMovesArray = PossibleMovesArray(24)

    private val transitions = arrayOf(
            intArrayOf( // 0
                    0b110_000_000_000_000_000_000_000,
                    0b100_000_000_100_000_000_000_000
            ),
            intArrayOf( // 1
                    0b110_000_000_000_000_000_000_000,
                    0b011_000_000_000_000_000_000_000,
                    0b010_010_000_000_000_000_000_000
            ),
            intArrayOf( // 2
                    0b011_000_000_000_000_000_000_000,
                    0b001_000_000_000_001_000_000_000
            ),
            intArrayOf( // 3
                    0b000_110_000_000_000_000_000_000,
                    0b000_100_000_010_000_000_000_000
            ),
            intArrayOf( // 4
                    0b000_110_000_000_000_000_000_000,
                    0b000_011_000_000_000_000_000_000,
                    0b010_010_000_000_000_000_000_000,
                    0b000_010_010_000_000_000_000_000
            ),
            intArrayOf( // 5
                    0b000_011_000_000_000_000_000_000,
                    0b000_001_000_000_010_000_000_000
            ),
            intArrayOf( // 6
                    0b000_000_110_000_000_000_000_000,
                    0b000_000_100_001_000_000_000_000
            ),
            intArrayOf( // 7
                    0b000_000_110_000_000_000_000_000,
                    0b000_000_011_000_000_000_000_000,
                    0b000_010_010_000_000_000_000_000
            ),
            intArrayOf( // 8
                    0b000_000_011_000_000_000_000_000,
                    0b000_000_001_000_100_000_000_000
            ),
            intArrayOf( // 9
                    0b000_000_000_110_000_000_000_000,
                    0b100_000_000_100_000_000_000_000,
                    0b000_000_000_100_000_000_000_100
            ),
            intArrayOf( // 10
                    0b000_000_000_110_000_000_000_000,
                    0b000_000_000_011_000_000_000_000,
                    0b000_100_000_010_000_000_000_000,
                    0b000_000_000_010_000_000_100_000
            ),
            intArrayOf( // 11
                    0b000_000_000_011_000_000_000_000,
                    0b000_000_100_001_000_000_000_000,
                    0b000_000_000_001_000_100_000_000
            ),
            intArrayOf( // 12
                    0b000_000_000_000_110_000_000_000,
                    0b000_000_001_000_100_000_000_000,
                    0b000_000_000_000_100_001_000_000
            ),
            intArrayOf( // 13
                    0b000_000_000_000_110_000_000_000,
                    0b000_000_000_000_011_000_000_000,
                    0b000_001_000_000_010_000_000_000,
                    0b000_000_000_000_010_000_001_000
            ),
            intArrayOf( // 14
                    0b000_000_000_000_011_000_000_000,
                    0b001_000_000_000_001_000_000_000,
                    0b000_000_000_000_001_000_000_001
            ),
            intArrayOf( // 15
                    0b000_000_000_000_000_110_000_000,
                    0b000_000_000_001_000_100_000_000
            ),
            intArrayOf( // 16
                    0b000_000_000_000_000_110_000_000,
                    0b000_000_000_000_000_011_000_000,
                    0b000_000_000_000_000_010_010_000
            ),
            intArrayOf( // 17
                    0b000_000_000_000_000_011_000_000,
                    0b000_000_000_000_100_001_000_000
            ),
            intArrayOf( // 18
                    0b000_000_000_000_000_000_110_000,
                    0b000_000_000_010_000_000_100_000
            ),
            intArrayOf( // 19
                    0b000_000_000_000_000_000_110_000,
                    0b000_000_000_000_000_000_011_000,
                    0b000_000_000_000_000_010_010_000,
                    0b000_000_000_000_000_000_010_010
            ),
            intArrayOf( // 20
                    0b000_000_000_000_000_000_011_000,
                    0b000_000_000_000_010_000_001_000
            ),
            intArrayOf( // 21
                    0b000_000_000_000_000_000_000_110,
                    0b000_000_000_100_000_000_000_100
            ),
            intArrayOf( // 22
                    0b000_000_000_000_000_000_000_110,
                    0b000_000_000_000_000_000_000_011,
                    0b000_000_000_000_000_000_010_010
            ),
            intArrayOf( // 23
                    0b000_000_000_000_000_000_000_011,
                    0b000_000_000_000_001_000_000_001
            )
    )

    fun progress(move: Int, other: Player) {
        when (stage) {
            Stage.SETTING -> {
                val moveBoard = move or (1 shl move)
                board = board or moveBoard
                stonesToSet--
                other.movesRemoving.add(move)
                addTransitions(movesJumpMove, move, board))

                if (player.board.has2Mills()) {
                    player.previousStage = Stage.SETTING
                    player.stage = Stage.REMOVE2
                } else if (player.board.hasMill()) {
                    player.previousStage = Stage.SETTING
                    player.stage = Stage.REMOVE1
                } else {
                    switchPlayers()
                }
            }
        }
    }

    fun moves(other: Player) {
        when (stage) {
            Stage.SETTING -> {
                val empties = (board or other.board).inv()
                for (i in 0..23) {
                    if (empties and (1 shl i) == 1 shl i) {
                        moves[i] = 1 shl i
                    }
                }
            }
            Stage.REMOVE1, Stage.REMOVE2 -> {
                for (i in 0..23) {
                    if (other.board and (1 shl i) == 1 shl i) {
                        moves[i] = 1 shl i
                    }
                }
            }
            Stage.JUMPING -> {
                
            }
        }
    }

    fun reset() {
        stonesToSet = 9
        amountOfStones = 9
        board = 0
        stage = Stage.SETTING
        // It's not necessary to set the previous stage
    }

    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun transitions(to: PossibleMovesArray, move: Int, withoutBoard: Int) {
        transitions[move].forEach {
            if ((it and withoutBoard) != 0) { // == the move is free on the board
                to.add(it)
            }
        }
    }
}