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
class HighScore : Information<Game2048> {
    private var highScore = 0

    override val name = "High score"
    override val value get() = highScore.format()

    override fun update(event: Event, basedOn: Game2048) =
            when (event) {
                Event.POST_GAME -> highScore = maxOf(highScore, basedOn.score)
                else            -> Unit
            }

    override fun toString() = asString()
}