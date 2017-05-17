package logic.information.general

import logic.information.Event
import logic.information.Information
import logic.information.asString
import logic.information.format
import utils.Stopwatch

/**
 * - mutable
 *
 * @param
 * @return
 */
class MovesPerSecond : Information<Unit> {
    companion object {
        private val SECOND = 1000L
    }

    private var movesPerSecond = 0
    private var movesTakenCurrently = 0
    private val timer = Stopwatch()

    override val name = "Moves per second"
    override val value = movesPerSecond.format()

    override fun update(event: Event, basedOn: Unit) {
        when (event) {
            Event.POST_MOVE ->
                if (timer.isTimeOver(SECOND)) {
                    movesPerSecond = movesTakenCurrently
                    movesTakenCurrently = 0
                    timer.start()
                } else {
                    movesTakenCurrently += 1
                }
            else -> Unit
        }
    }

    override fun toString() = asString()
}