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
class GamesPlayed : Information<Any> {
    private var gamesPlayed = 0

    override val name = "Games played"
    override val value get() = gamesPlayed.format()

    override fun update(event: Event, basedOn: Any) =
            when (event) {
                Event.POST_GAME -> gamesPlayed += 1
                else            -> Unit
            }

    override fun toString() = asString()
}