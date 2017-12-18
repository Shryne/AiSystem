package base.mill

import java.util.*

/**
 * - mutable
 *
 * @param
 * @return
 */
class MillImpl : MillPlay {
    companion object {
        // bit masks to get the source and target value from the move in the progress method.
        private val SOURCE_MASK = 0x1_1111
        private val TARGET_SHIFT = 5
        private val TARGET_MASK = SOURCE_MASK shl TARGET_SHIFT
    }

    override val map: IntArray
        get() {
            val result = IntArray(24)
            for (i in 0..23) {
                result[i] = if (p1.board and (1 shl i) == 1 shl i) 1 else 0
            }
            for (i in 0..23) {
                result[i] = if (p2.board and (1 shl i) == 1 shl i) 2 else result[i]
            }
            return result
        }

    private var otherPlayer = Player()
    override var player = Player()
        private set

    private val p1 = player
    private val p2 = otherPlayer

    override val possibleMoves = logic.sequence.PossibleMovesArray(24)

    private val singleLines = hashSetOf(
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

    private val doubleLines = hashSetOf(
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

    /**
     * - mutable - changes the state of the board and player
     * @param Move consists of two 5 bit values, the first for the source and the second for the target position. The
     * target is only used in all stages besides the SETTING stage. The format is: 0000_0000_..._[second move 5 bits]
     * [first move 5 bits]
     */
    override fun progress(move: Int) {
        player.board = player.board or (1 shl move)
        switchPlayers()
    }

    override fun reset() {

    }

    override fun toString() =
            "Mill (Player stage: ${player.stage}, other player stage: ${otherPlayer.stage}):\n${mapToString()}"

    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private fun Int.hasMill() = this in singleLines

    private fun Int.has2Mills() = this in doubleLines

    private fun switchPlayers() {
        val temp = player
        player = otherPlayer
        otherPlayer = temp
    }

    /**
     * source: https://github.com/theoriginalbit/nine-mens-morris/blob/master/src/main/java/asburyj/nmm/View.java
     * Note: I used consolas as font to get the correct view.
     */
    private fun mapToString() =
            System.out.format(
                    "┏━━━┓             ┏━━━┓             ┏━━━┓\n" +
                    "┃ %d ┣━━━━━━━━━━━━━┫ %d ┣━━━━━━━━━━━━━┫ %d ┃\n" +
                    "┗━┳━┛             ┗━┳━┛             ┗━┳━┛\n" +
                    "  ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃\n" +
                    "  ┃   ┃ %d ┣━━━━━━━┫ %d ┣━━━━━━━┫ %d ┃   ┃\n" +
                    "  ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃\n" +
                    "  ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃\n" +
                    "  ┃     ┃   ┃ %d ┣━┫ %d ┣━┫ %d ┃   ┃     ┃\n" +
                    "  ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃\n" +
                    "┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓\n" +
                    "┃ %d ┣━┫ %d ┣━┫ %d ┃       ┃ %d ┣━┫ %d ┣━┫ %d ┃\n" +
                    "┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛\n" +
                    "  ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃\n" +
                    "  ┃     ┃   ┃ %d ┣━┫ %d ┣━┫ %d ┃   ┃     ┃\n" +
                    "  ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃\n" +
                    "  ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃\n" +
                    "  ┃   ┃ %d ┣━━━━━━━┫ %d ┣━━━━━━━┫ %d ┃   ┃\n" +
                    "  ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃\n" +
                    "┏━┻━┓             ┏━┻━┓             ┏━┻━┓\n" +
                    "┃ %d ┣━━━━━━━━━━━━━┫ %d ┣━━━━━━━━━━━━━┫ %d ┃\n" +
                    "┗━━━┛             ┗━━━┛             ┗━━━┛\n",
                    *map.toTypedArray()
            )
}