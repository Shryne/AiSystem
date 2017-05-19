package logic.information.game.game2048

import base._2048.Game2048
import logic.information.Event
import logic.information.Information
import logic.information.asString
import logic.information.percentage

/**
 * - mutable
 *
 * @param
 * @return
 */
class HighestTileProbability(val highestTileReached: HighestTileReached) : Information<Game2048> {
    private var gamesPlayed = 0

    override val name = "Highest tile probability"
    override val value get() = (highestTileReached.typedValue / gamesPlayed.toDouble()).percentage()

    override fun update(event: Event, basedOn: Game2048) {
        when (event) {
            Event.POST_GAME -> gamesPlayed++
            else            -> Unit
        }
    }

    override fun toString() = asString()
}