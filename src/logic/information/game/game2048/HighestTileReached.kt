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
class HighestTileReached : Information<Game2048<*>> {
    private var highestTile = 0
    private var amount = 0

    override val name = "Highest tile reached"
    override val value get() = amount.format()
    val typedValue get() = amount

    override fun update(event: Event, basedOn: Game2048<*>) =
            when (event) {
                Event.POST_GAME -> {
                    val currentHighestTile = basedOn.highestTile
                    if (currentHighestTile > highestTile) {
                        highestTile = currentHighestTile
                        amount = 1
                    } else if (currentHighestTile == highestTile) {
                        amount += 1
                    } else {
                        Unit
                    }
                }
                else            -> Unit
            }

    override fun toString() = asString()
}