package base.mill

import utils.fill

/*
weaknesses:
- There is no syntactical link between the mill index and the mills, even though every index (/move) is part of the mill
- The mills are defined by hand, not by formula -> error prone
 */

/**
 * Methods for the board of the mill game. This board is part of the player and doesn't contain the full representation
 * of a games board.
 *
 * The board is inside the first 24 bits of an int and the indices are from right to left.
 * Board as an int: 0b000_000_000_000_000_000_000_000
 * Binary indices: 23'22'21_20'19'28_17'16'15_14'13'12_11'10'9_8'7'6_5'4'3_3'2'1
 * Indices in "reality":
 *
 * ┏━━━┓             ┏━━━┓             ┏━━━┓
 * ┃ 0 ┣━━━━━━━━━━━━━┫ 1 ┣━━━━━━━━━━━━━┫ 2 ┃
 * ┗━┳━┛             ┗━┳━┛             ┗━┳━┛
 *   ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃
 *   ┃   ┃ 3 ┣━━━━━━━┫ 4 ┣━━━━━━━┫ 5 ┃   ┃
 *   ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃
 *   ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃
 *   ┃     ┃   ┃ 6 ┣━┫ 7 ┣━┫ 8 ┃   ┃     ┃
 *   ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃
 * ┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓
 * ┃ 9 ┣━┫ 10┣━┫ 11┃       ┃ 12┣━┫ 13┣━┫ 14┃
 * ┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛
 *   ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃
 *   ┃     ┃   ┃ 15┣━┫ 16┣━┫ 17┃   ┃     ┃
 *   ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃
 *   ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃
 *   ┃   ┃ 18┣━━━━━━━┫ 19┣━━━━━━━┫ 20┃   ┃
 *   ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃
 * ┏━┻━┓             ┏━┻━┓             ┏━┻━┓
 * ┃ 21┣━━━━━━━━━━━━━┫ 22┣━━━━━━━━━━━━━┫ 23┃
 * ┗━━━┛             ┗━━━┛             ┗━━━┛
 */
typealias Board = Int

private const val MILL_H0 = 0b000_000_000_000_000_000_000_111
private const val MILL_H1 = 0b000_000_000_000_000_000_111_000
private const val MILL_H2 = 0b000_000_000_000_000_111_000_000
private const val MILL_H3 = 0b000_000_000_000_111_000_000_000
private const val MILL_H4 = 0b000_000_000_111_000_000_000_000
private const val MILL_H5 = 0b000_000_111_000_000_000_000_000
private const val MILL_H6 = 0b000_111_000_000_000_000_000_000
private const val MILL_H7 = 0b111_000_000_000_000_000_000_000

private const val MILL_V0 = (1 shl 0) or (1 shl 9) or (1 shl 21)
private const val MILL_V1 = (1 shl 3) or (1 shl 10) or (1 shl 18)
private const val MILL_V2 = (1 shl 6) or (1 shl 11) or (1 shl 15)
private const val MILL_V3 = (1 shl 1) or (1 shl 4) or (1 shl 7)
private const val MILL_V4 = (1 shl 16) or (1 shl 19) or (1 shl 22)
private const val MILL_V5 = (1 shl 8) or (1 shl 12) or (1 shl 17)
private const val MILL_V6 = (1 shl 5) or (1 shl 13) or (1 shl 20)
private const val MILL_V7 = (1 shl 2) or (1 shl 14) or (1 shl 23)

private val mills = hashMapOf(
        // row: 0
        move(0).to(
                arrayOf(MILL_H0, MILL_V0)
        ),
        move(1).to(
                arrayOf(MILL_H0, MILL_V3)
        ),
        move(2).to(
                arrayOf(MILL_H0, MILL_V7)
        ),

        // row: 1
        move(3).to(
                arrayOf(MILL_H1, MILL_V1)
        ),
        move(4).to(
                arrayOf(MILL_H1, MILL_V3)
        ),
        move(5).to(
                arrayOf(MILL_H1, MILL_V6)
        ),

        // row: 2
        move(6).to(
                arrayOf(MILL_H2, MILL_V2)
        ),
        move(7).to(
                arrayOf(MILL_H2, MILL_V3)
        ),
        move(8).to(
                arrayOf(MILL_H2, MILL_V5)
        ),

        // row: 3
        move(9).to(
                arrayOf(MILL_H3, MILL_V0)
        ),
        move(10).to(
                arrayOf(MILL_H3, MILL_V1)
        ),
        move(11).to(
                arrayOf(MILL_H3, MILL_V2)
        ),

        // row: 4
        move(12).to(
                arrayOf(MILL_H4, MILL_V5)
        ),
        move(13).to(
                arrayOf(MILL_H4, MILL_V6)
        ),
        move(14).to(
                arrayOf(MILL_H4, MILL_V7)
        ),

        // row: 5
        move(15).to(
                arrayOf(MILL_H5, MILL_V2)
        ),
        move(16).to(
                arrayOf(MILL_H5, MILL_V4)
        ),
        move(17).to(
                arrayOf(MILL_H5, MILL_V5)
        ),

        // row: 6
        move(18).to(
                arrayOf(MILL_H6, MILL_V1)
        ),
        move(19).to(
                arrayOf(MILL_H6, MILL_V4)
        ),
        move(20).to(
                arrayOf(MILL_H6, MILL_V6)
        ),

        // row: 7
        move(21).to(
                arrayOf(MILL_H7, MILL_V0)
        ),
        move(22).to(
                arrayOf(MILL_H7, MILL_V4)
        ),
        move(23).to(
                arrayOf(MILL_H7, MILL_V7)
        )
)

private const val DOUBLE_MILL_0 = MILL_H0 or MILL_V0
private const val DOUBLE_MILL_1 = MILL_H0 or MILL_V3
private const val DOUBLE_MILL_2 = MILL_H0 or MILL_V7
private const val DOUBLE_MILL_3 = MILL_H1 or MILL_V1
private const val DOUBLE_MILL_4 = MILL_H1 or MILL_V3
private const val DOUBLE_MILL_5 = MILL_H1 or MILL_V6
private const val DOUBLE_MILL_6 = MILL_H2 or MILL_V2
private const val DOUBLE_MILL_7 = MILL_H2 or MILL_V3
private const val DOUBLE_MILL_8 = MILL_H2 or MILL_V5
private const val DOUBLE_MILL_9 = MILL_H3 or MILL_V0
private const val DOUBLE_MILL_10 = MILL_H3 or MILL_V1
private const val DOUBLE_MILL_11 = MILL_H3 or MILL_V2
private const val DOUBLE_MILL_12 = MILL_H4 or MILL_V5
private const val DOUBLE_MILL_13 = MILL_H4 or MILL_V6
private const val DOUBLE_MILL_14 = MILL_H4 or MILL_V7
private const val DOUBLE_MILL_15 = MILL_H5 or MILL_V2
private const val DOUBLE_MILL_16 = MILL_H5 or MILL_V4
private const val DOUBLE_MILL_17 = MILL_H5 or MILL_V5
private const val DOUBLE_MILL_18 = MILL_H6 or MILL_V1
private const val DOUBLE_MILL_19 = MILL_H6 or MILL_V4
private const val DOUBLE_MILL_20 = MILL_H6 or MILL_V6
private const val DOUBLE_MILL_21 = MILL_H7 or MILL_V0
private const val DOUBLE_MILL_22 = MILL_H7 or MILL_V4
private const val DOUBLE_MILL_23 = MILL_H7 or MILL_V7

private val doubleMills = hashMapOf(
        move(0).to(DOUBLE_MILL_0),
        move(1).to(DOUBLE_MILL_1),
        move(2).to(DOUBLE_MILL_2),

        move(3).to(DOUBLE_MILL_3),
        move(4).to(DOUBLE_MILL_4),
        move(5).to(DOUBLE_MILL_5),

        move(6).to(DOUBLE_MILL_6),
        move(7).to(DOUBLE_MILL_7),
        move(8).to(DOUBLE_MILL_8),

        move(9).to(DOUBLE_MILL_9),
        move(10).to(DOUBLE_MILL_10),
        move(11).to(DOUBLE_MILL_11),

        move(12).to(DOUBLE_MILL_12),
        move(13).to(DOUBLE_MILL_13),
        move(14).to(DOUBLE_MILL_14),

        move(15).to(DOUBLE_MILL_15),
        move(16).to(DOUBLE_MILL_16),
        move(17).to(DOUBLE_MILL_17),

        move(18).to(DOUBLE_MILL_18),
        move(19).to(DOUBLE_MILL_19),
        move(20).to(DOUBLE_MILL_20),

        move(21).to(DOUBLE_MILL_21),
        move(22).to(DOUBLE_MILL_22),
        move(23).to(DOUBLE_MILL_23)
)

/**
 * Checks if the given move created a mill on the board. Attention: The board must already contain the move!
 * The mills are hashed and not calculated for better performance, but for this reason this method is not thread safe.
 *
 * The board and move are only checked in assert mode for performance reasons!
 * -> tested <-
 */
fun Board.isMill(move: Move): Boolean {
    assert(isValidBoard and ((board and move) == move) and isValidMove)
    return mills[move]!!.any {
        (this and it) == it
    }
}

/**
 * Checks if the given move created a double mill on the board. Attention: The board must already contain the move!
 * The mills are hashed and not calculated for better performance, but for this reason this method is not thread safe.
 *
 * The board and move are only checked in assert mode for performance reasons!
 * -> tested <-
 */
fun Board.isDoubleMill(move: Move): Boolean {
    assert(isValidBoard and ((board and move) == move) and isValidMove)
    return (doubleMills[move]!! and this) == doubleMills[move]
}

/**
 * Returns the amount of the stones on the board.
 *
 * The board is only checked in assert mode for performance reasons!
 * -> tested <-
 * source: https://stackoverflow.com/a/109025
 */
val Board.stoneAmount: Int
    get() {
        assert(isValidBoard)

        var i = this - ((this shr 1) and 0x5555_5555)
        i = (i and 0x3333_3333) + ((i shr 2) and 0x3333_3333)
        return (((i + (i shr 4)) and 0x0F0F_0F0F) * 0x0101_0101) shr 24
    }

// TODO
val Board.isValidBoard get() = true

fun Board.boardBinaryPrint(): Board {
    var result = ""
    val binary = Integer.toBinaryString(this).fill("0", MILL_FIELDS)
    binary.forEachIndexed {
        index, c ->
        result += if (index.rem(3) == 0) "_$c" else c
    }
    println("Board: " + result.substring(1 until result.length))
    return this
}