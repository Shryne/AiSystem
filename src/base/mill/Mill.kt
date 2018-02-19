package base.mill

typealias Game = Long
typealias Move = Int

typealias Mill = Long

const val MILL_FIELDS = 24
private const val ANSI_RESET = "\u001B[0m"
private const val ANSI_RED = "\u001B[31m"

/**
 * Returns a Long that forms the mill game state. The current player is on the last 32 bits and the current on the
 * first.
 *
 * -> tested <-
 */
fun mill(player: Player, otherPlayer: Player) =
        (player.toLong() shl PLAYER_BIT_AMOUNT) or (otherPlayer.toLong() and 0xFFFF_FFFF) /* if the otherPlayer is
        player 1 the first bit will be set and the long cast will set all new leading bits to 1. I have to use the 32
        bit mask for this reason. */

fun mill() = mill(player(0), player(1))

fun Mill.moves(to: MoveArray) {
    player.stage.moves(this, to)
}

val Mill.over: Boolean get() = player.lost || otherPlayer.lost

val Mill.board: Long
    get() = ((player.board.toLong() shl MILL_FIELDS) and (otherPlayer.board.toLong())) and 0xFFFFFF_FFFFFF

fun Mill.moved(move: Move) = player.stage.moved(this, move)

val Mill.player get() = ((this shr PLAYER_BIT_AMOUNT) and 0xFFFF_FFFF).toInt()
val Mill.otherPlayer get() = (this and 0xFFFF_FFFF).toInt()


/**
 * Fills the given array based on both players boards inside the given long.
 *
 * @param to The given Array needs
 *
 * This method has only an assertion check for performance reasons!
 */
fun Mill.map(to: IntArray): IntArray {
    assert(to.size == MILL_FIELDS)

    val playerNumber = player.num
    val otherPlayerNumber = otherPlayer.num

    for (i in 0..23) {
        val field = move(i)
        to[i] =
                when (field) {
                    (player.board and field)      -> playerNumber
                    (otherPlayer.board and field) -> otherPlayerNumber
                    else                          -> 9
                }
    }

    return to
}

/**
 * Source: https://github.com/theoriginalbit/nine-mens-morris/blob/master/src/main/java/asburyj/nmm/View.java
 * Note: I used consolas as font to get the correct view.
 *
 * This isn't intended to be efficient and it surly isn't. There are no tests written for this, because
 * it hasn't a high priority.
 */
val Mill.asString: String
    get() {
        assert(isValidMill) {
            println("The mill isn't valid: $this")
        }
        return String.format(
                "┏━━━┓             ┏━━━┓             ┏━━━┓\n" +
                        "┃ %s ┣━━━━━━━━━━━━━┫ %s ┣━━━━━━━━━━━━━┫ %s ┃\n" +
                        "┗━┳━┛             ┗━┳━┛             ┗━┳━┛\n" +
                        "  ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃\n" +
                        "  ┃   ┃ %s ┣━━━━━━━┫ %s ┣━━━━━━━┫ %s ┃   ┃\n" +
                        "  ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃\n" +
                        "  ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃\n" +
                        "  ┃     ┃   ┃ %s ┣━┫ %s ┣━┫ %s ┃   ┃     ┃\n" +
                        "  ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃\n" +
                        "┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓\n" +
                        "┃ %s ┣━┫ %s ┣━┫ %s ┃       ┃ %s ┣━┫ %s ┣━┫ %s ┃\n" +
                        "┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛\n" +
                        "  ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃\n" +
                        "  ┃     ┃   ┃ %s ┣━┫ %s ┣━┫ %s ┃   ┃     ┃\n" +
                        "  ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃\n" +
                        "  ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃\n" +
                        "  ┃   ┃ %s ┣━━━━━━━┫ %s ┣━━━━━━━┫ %s ┃   ┃\n" +
                        "  ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃\n" +
                        "┏━┻━┓             ┏━┻━┓             ┏━┻━┓\n" +
                        "┃ %s ┣━━━━━━━━━━━━━┫ %s ┣━━━━━━━━━━━━━┫ %s ┃\n" +
                        "┗━━━┛             ┗━━━┛             ┗━━━┛\n",
                *map(IntArray(MILL_FIELDS)).map {
                        when(it) {
                            0 -> ANSI_RED + "0" + ANSI_RESET
                            1 -> ANSI_RED + "1" + ANSI_RESET
                            else -> " "
                        }
                }.toTypedArray()
        ) +
                when {
                    player.lost      -> "Player ${otherPlayer.num} won"
                    otherPlayer.lost -> "Player ${player.num} won"
                    else             -> ""
                }
    }

//TODO
val isValidMill get() = true

fun Mill.millBinaryPrint(tabAmount: Int = 0): Mill {
    val additionalTabs = "\t".repeat(tabAmount)

    println("${additionalTabs}Mill:")
    player.playerBinaryPrint(tabAmount + 1)
    println()
    otherPlayer.playerBinaryPrint(tabAmount + 1)
    return this
}