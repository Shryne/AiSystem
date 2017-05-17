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
class AvgMovesPerSecond : Information<Unit> {
    companion object {
        private val SECOND = 1000L
        private val MAX_AMOUNT_FOR_AVG = 20
    }

    private var avgMovesPerSecond = 0
    private var movesTakenCurrently = 0
    private var counter = 0
    private val timer = Stopwatch()

    override val name = "Avg moves per second"
    override val value = avgMovesPerSecond.format()

    override fun update(event: Event, basedOn: Unit) {
        when (event) {
            Event.POST_MOVE ->
                if (timer.isTimeOver(SECOND)) {
                    avgMovesPerSecond = (avgMovesPerSecond * counter + movesTakenCurrently) / (counter + 1)
                    movesTakenCurrently = 0
                    counter = minOf(counter + 1, MAX_AMOUNT_FOR_AVG)
                    timer.start()
                } else {
                    movesTakenCurrently += 1
                }
            else            -> Unit
        }
    }

    override fun toString() = asString()
}