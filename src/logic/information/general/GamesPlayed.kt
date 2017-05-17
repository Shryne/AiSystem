package logic.information.general

import logic.information.Event
import logic.information.Information
import logic.information.asString

/**
 * - mutable
 *
 * @param
 * @return
 */
class GamesPlayed : Information<Unit> {
    private var gamesPlayed = 0

    override val name = "Games played"
    override val value get() = "$gamesPlayed"

    override fun update(event: Event, basedOn: Unit) =
            when (event) {
                Event.POST_GAME -> gamesPlayed += 1
                else            -> Unit
            }

    override fun toString() = asString()
}