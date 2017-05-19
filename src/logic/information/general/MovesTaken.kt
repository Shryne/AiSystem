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
class MovesTaken : Information<Any> {
    private var movesTaken = 0

    override val name = "Moves taken"
    override val value get() = movesTaken.format()

    override fun update(event: Event, basedOn: Any) =
            when (event) {
                Event.POST_MOVE -> movesTaken += 1
                else            -> Unit
            }

    override fun toString() = asString()
}