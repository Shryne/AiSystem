package logic.information.game.game2048

import base._2048.Game2048
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
class AvgScore : Information<Game2048<*>> {
    private var avgScore = 0.0
    private var gamesPlayed = 0

    override val name = "Average score"
    override val value get() = avgScore.format()

    override fun update(event: Event, basedOn: Game2048<*>) =
            when (event) {
                Event.POST_GAME -> {
                    avgScore = (avgScore * gamesPlayed + basedOn.score) / (gamesPlayed + 1)
                    gamesPlayed += 1
                }
                else            -> Unit
            }

    override fun toString() = asString()
}