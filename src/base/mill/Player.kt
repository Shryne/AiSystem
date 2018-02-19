package base.mill

import base.mill.stage.*

/**
 * The Player is coded in an Int for performance reasons. He has four variables: board, stonesToSet, stage and
 * num.
 * See [player]
 */
typealias Player = Int

const val PLAYER_BIT_AMOUNT = 32
const val STONES_TO_SET_BIT_AMOUNT = 4
const val STAGES_BIT_AMOUNT = 3

/**
 * Creates an int with a special coding that represents a player. The first 24 bits are used for the board, the next
 * 4 bits (0 - 9) for the left stones to set during the setting phase, the following 3 bits are used for the stages
 * (j, m, r1, r2, s -> 5, 3 bits) and the last bit is used for the players identity (0 or 1).
 *
 * This method checks the input only in assert mode for performance reasons!
 * -> tested <-
 *
 * @param stage The current stage of the player.
 * @param stonesToSet The stones left to be set. This values must be between 1 and 9 (inclusive) during the setting
 * stage and equal to 0 during the other stages except the removing stages. The last is because the removing stages fall
 * back to the previous stage the player had and it can possibly and is determined by the stonesToSet variable if he
 * falls back on the setting stage.
 * @param board Contains all stones this player has on the board. This method doesn't and can't ensure that the board
 * of this player doesn't overlap with the other players board.
 * @param num The number of the player to distinguish them.
 *
 * @return The player inside an integer.
 */
fun player(num: Int, stage: Stage, stonesToSet: Int, board: Board): Int {
    assert(board.isValidBoard and
            when {
                stage == Setting                        -> (0 < stonesToSet) and (stonesToSet <= 9)
                (stage == Moving) or (stage == Jumping) -> stonesToSet == 0
                else                                    -> true
            }
    ) {
        "Error. Values: [isValidBoard: ${board.isValidBoard}, stage: $stage, stonesToSet: $stonesToSet}"
    }

    return (num shl (MILL_FIELDS + STAGES_BIT_AMOUNT + STONES_TO_SET_BIT_AMOUNT)) or
            (stage.order shl (MILL_FIELDS + STONES_TO_SET_BIT_AMOUNT)) or
            (stonesToSet shl MILL_FIELDS) or
            board
}

// TODO: Add constants
fun player(num: Int) = player(num, Setting, 9, 0)

/**
 * Extracts the stonesToSet value from the player.
 *
 * -> tested <-
 * This method checks the input only in assert mode for performance reasons!
 */
val Player.stonesToSet: Int
    get() {
        assert(isValidPlayer)
        return (this shr MILL_FIELDS) and 0xF
    }

/**
 * Extracts the board from the player.
 *
 * -> tested <-
 * This method checks the input only in assert mode for performance reasons!
 */
val Player.board: Int
    get() {
        assert(isValidPlayer)
        return this and 0xFFFFFF
    }

/**
 * Extracts the stage value from the player.
 *
 * -> tested <-
 * This method checks the input only in assert mode for performance reasons!
 */
val Player.stage: Stage
    get() {
        assert(isValidPlayer)
        return when ((this shr (MILL_FIELDS + STONES_TO_SET_BIT_AMOUNT)) and 0b111) {
            0    -> Setting
            1    -> Moving
            2    -> Jumping
            3    -> Removing
            else -> throw IllegalStateException("Stage must be between 0 and 4 but is $this")
        }
    }

val Player.num: Int
    get() {
        assert(isValidPlayer)
        return (this shr (MILL_FIELDS + STONES_TO_SET_BIT_AMOUNT + STAGES_BIT_AMOUNT)) and 0x1 /* to get rid of the sign
        bit */
    }

val Player.isValidPlayer get() = true

val Player.lost get() = stage != Setting && board.stoneAmount < 3

fun Player.playerBinaryPrint(tabAmount: Int = 0): Player {
    val additionalTabs = "\t".repeat(tabAmount)

    var result = "${additionalTabs}Player:\n"
    result += "$additionalTabs\tNum: $num\n"
    result += "$additionalTabs\tStage: $stage\n"
    result += "$additionalTabs\tStonesToSet: $stonesToSet\n"
    result += "$additionalTabs\t"
    print(result)
    board.boardBinaryPrint()
    return this
}