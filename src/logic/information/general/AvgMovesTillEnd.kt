package logic.information.general

import logic.information.Event
import logic.information.Information
import logic.information.asString
import logic.information.format

/**
 * - mutable
 *
 * @param
 * @return
 */
class AvgMovesTillEnd : Information<Unit> {
    private var avgMoves = 0.0
    private var gamesPlayed = 0
    private var moveCounter = 0

    override val name = "Average moves till end"
    override val value = avgMoves.format()

    override fun update(event: Event, basedOn: Unit) =
            when (event) {
                Event.POST_MOVE -> moveCounter += 1
                Event.POST_GAME -> {
                    avgMoves = (avgMoves * gamesPlayed + moveCounter) / (gamesPlayed + 1)
                    gamesPlayed++
                    moveCounter = 0
                }
                else            -> Unit
            }

    override fun toString() = asString()
}