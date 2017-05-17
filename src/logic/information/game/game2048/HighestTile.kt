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
class HighestTile : Information<Game2048> {
    private var highestTile = 0

    override val name = "Highest tile"
    override val value get() = highestTile.format()

    override fun update(event: Event, basedOn: Game2048) =
        when (event) {
            Event.POST_GAME -> highestTile = Math.max(highestTile, basedOn.highestTile)
            else            -> Unit
        }

    override fun toString() = asString()
}