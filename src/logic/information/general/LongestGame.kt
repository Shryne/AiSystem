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
class LongestGame : Information<Unit> {
    private var mostMoves = 0
    private var currentMoves = 0

    override val name = "Longest game"
    override val value = mostMoves.format()

    override fun update(event: Event, basedOn: Unit) =
            when (event) {
                Event.POST_MOVE -> currentMoves += 1
                Event.POST_GAME -> {
                    mostMoves = maxOf(mostMoves, currentMoves)
                    currentMoves = 0
                }
                else            -> Unit
            }

    override fun toString() = asString()
}